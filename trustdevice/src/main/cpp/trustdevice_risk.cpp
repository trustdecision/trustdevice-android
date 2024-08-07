//
// Created by zuchangwu on 2023/4/10.
//

#include "trustdevice_risk.h"

int readTracePid(const char *file_path) {
    int tracerPid = 0;
    std::ifstream status_file(file_path);
    if (!status_file) {
        return tracerPid;
    }
    std::string line;
    while (std::getline(status_file, line)) {
        if (line.find("TracerPid:") == 0) {
            tracerPid = std::stoi(line.substr(11));
            break;
        }
    }
    status_file.close();
    return tracerPid;
}

bool detectTracePid() {
    const char *status_path = "/proc/self/status";
    int tracerPid = readTracePid(status_path);
    if (tracerPid != 0) {
        return true;
    }
    return false;
}

bool detectTaskTracerPid() {
    pid_t pid = getpid();
    char buffer[512];
    const char *format = "/proc/%d/task/%d/status";
    std::sprintf(buffer, format, pid, pid);
    int tracerPid = readTracePid(buffer);
    if (tracerPid != 0) {
        return true;
    }
    return false;
}

jint detect_debug(JNIEnv *__unused, jobject __unused) {
    int result = 0;
    if (detectTracePid()) {
        result |= 0x1 << 1;
    }
    if (detectTaskTracerPid()) {
        result |= 0x1 << 2;
    }
    return static_cast<jint>(result);
}

size_t detect_frida(char *hook_method, const size_t max_length) {
    const char *libc_method_names[] = {"strcat", "fopen", "open", "read", "strcmp", "strstr", "fgets",
                                 "access", "ptrace", "__system_property_get"};

#ifdef __LP64__
    const char *libc_so_path = "/system/lib64/libc.so";
#else
    const char *libc_so_path = "/system/lib/libc.so";
#endif
    void *handler = dlopen(libc_so_path, RTLD_NOW);
    if (!handler) {
        return 0;
    }
    for (const char *method_name: libc_method_names) {
        void *method_sym = dlsym(handler, method_name);
        if (method_sym == nullptr) {
            continue;
        }
        auto *bytecode = reinterpret_cast<uintptr_t *>(method_sym);
        int access = mem_read_access_by_maps(bytecode, 16);
        if (access == 0) {
            continue;
        }
        operation_type operation = *bytecode;
        if (operation == trampoline_code) {
            size_t remaining_length = max_length - strlen(method_name);
            snprintf(hook_method + strlen(hook_method), remaining_length, "%s,", method_name);
        }
    }
    dlclose(handler);
    void *internal_method_names[] = {(void *) detect_debug, (void *) detectTaskTracerPid,
                               (void *) detectTracePid, (void *) readTracePid, (void *) detect_hook,
                               (void *) detect_frida};
    size_t internal_method_len = sizeof(internal_method_names) / sizeof(internal_method_names[0]);
    for (size_t i = 0; i < internal_method_len; ++i) {
        auto *bytecode = reinterpret_cast<uintptr_t *>(internal_method_names[i]);
        int access = mem_read_access_by_maps(bytecode, 16);
        if (access == 0) {
            continue;
        }
        operation_type operation = *bytecode;
        if (operation == trampoline_code) {
            std::string str = std::to_string(i);
            size_t remaining_length = max_length - str.size();
            snprintf(hook_method + strlen(hook_method), remaining_length, "%zu,", i);
        }
    }
    size_t str_len = strlen(hook_method);
    if (str_len > 0 && hook_method[str_len - 1] == ',') {
        hook_method[str_len - 1] = '\0';
    }
    LOGD("hook methods: %s",hook_method);
    return str_len;
}

jstring detect_hook(JNIEnv *env, jobject __unused) {
    const size_t max_length = 512;
    char frida_hook_method[max_length] = {};
    detect_frida(frida_hook_method, max_length);
    return env->NewStringUTF(frida_hook_method);
}

jboolean detectMagiskByMount(__unused JNIEnv *env, jobject __unused) {
    const char *mounts_path = "/proc/self/mounts";
    const char *mountinfo_path = "/proc/self/mountinfo";
    const char *mountstats_path = "/proc/self/mountstats";

    struct stat mounts_stat;
    stat(mounts_path,&mounts_stat);

    struct stat mountinfo_stat;
    stat(mountinfo_path,&mountinfo_stat);

    struct stat mountstats_stat;
    stat(mountstats_path,&mountstats_stat);

    if (!((mounts_stat.st_mtime == mountinfo_stat.st_mtime) &&
         (mountinfo_stat.st_mtime == mountstats_stat.st_mtime))) {
        LOGD("detectMagiskByMount found abnormality in mounted files");
        return JNI_TRUE;
    }

    return JNI_FALSE;
}

jboolean checkBinInEnv(JNIEnv *env, jobject __unused klass,jstring binName) {
    char *sys_env = getenv("PATH");
    if(env == nullptr){
        return false;
    }
    const char *binNameChs = env->GetStringUTFChars(binName,nullptr);
    const size_t ENV_PATH_LEN = 64;
    char *curPath;
    char envItemPath[ENV_PATH_LEN] = {0};
    char *envPath = strdup(sys_env);
    bool accessible = false;
    if (envPath != nullptr) {
        curPath = strtok(envPath, ":");
        while (curPath != nullptr) {
            memset(envItemPath,'\0',ENV_PATH_LEN);
            snprintf(envItemPath, ENV_PATH_LEN, "%s/%s", curPath, binNameChs);

            int accessStatus = access(envItemPath,X_OK);

            LOGD("%s path: %s,bin file access status: %d",__FUNCTION__ ,envItemPath, accessStatus);

            if (accessStatus != -1) {
                accessible = true;
                break;
            }
            curPath = strtok(nullptr, ":");
        }
        free(envPath);
    }

    env->ReleaseStringUTFChars(binName,binNameChs);

    return accessible;
}