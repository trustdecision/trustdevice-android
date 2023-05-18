#include <jni.h>
#include "detection_risk.h"
#include "utils.h"

extern "C" {

// Define JNI methods to be registered
static JNINativeMethod jniMethods[] = {
        {"detectDebug", "()I", (void *) detect_debug},
        {"detectHook", "()Ljava/lang/String;", (void *) detect_hook},
        {"getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", (void *) get_property},
};

// Define JNI library registration function
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = nullptr;
    jint result = -1;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return result;
    }

    jclass clazz = env->FindClass("cn/tongdun/mobrisk/core/tools/JNIHelper");
    if (clazz == nullptr) {
        return result;
    }
    if (env->RegisterNatives(clazz, jniMethods, sizeof(jniMethods) / sizeof(jniMethods[0])) < 0) {
        return result;
    }

    result = JNI_VERSION_1_6;

    return result;
}

}