//
// Created by zuchangwu on 2023/4/7.
//

#include "trustdevice_utils.h"


char *get_current_thread_name() {
    static char thread_name[256] = {0};
    memset(thread_name,'\0',256);
    prctl(PR_GET_NAME, (unsigned long)thread_name);
    return (char *)thread_name;
}

jstring get_property(JNIEnv *env, jobject __unused, jstring _key, jstring _default_value) {
    const char *c_key = env->GetStringUTFChars(_key, nullptr);
    char value[128] = "";
    int length = __system_property_get(c_key, value);
    if (length < 0) {
        return _default_value;
    }
    return env->NewStringUTF(value);
}

/**
 * Parse every step of the map, observe the permission part, it is no permission
 * 0: not accessible; 1: accessibles
 */
bool mem_read_access_by_maps(void *read_addr, size_t len) {
    if(read_addr == nullptr)
        return false;
#if defined(__LP64__)
    uint64_t addr = (uint64_t)read_addr;
    uint64_t start_addr = 0, end_addr = 0, last_addr = 0;
#else
    uint32_t addr = (uint32_t)read_addr;
    uint32_t start_addr = 0, end_addr = 0, last_addr = 0;
#endif
    char access = 0, buff[1024];
    FILE *fmap;
    fmap = fopen("/proc/self/maps", "r");
    if(!fmap){
        LOGW("open /proc/self/maps file failed!");
        return false;
    }

    while(fgets(buff, sizeof(buff)-1, fmap) != nullptr) {
#if defined(__LP64__)
        sscanf(buff, "%lx-%lx %c", &start_addr, &end_addr, &access);
#else
        sscanf(buff, "%x-%x %c", &start_addr, &end_addr, &access);
#endif
        if((addr >= start_addr) && (addr <= end_addr)){
            if('r' != access){
                fclose(fmap);
                return false;
            }

            if((addr + len) < end_addr){
                fclose(fmap);
                return true;
            }else {
                last_addr = end_addr;
                len = len - (end_addr - addr);
                addr = last_addr;
            }
        }
    }

    fclose(fmap);
    return false;
}