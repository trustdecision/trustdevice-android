#include <jni.h>
#include "detection_risk.h"

extern "C" {

jobject entrance(JNIEnv *env, jclass clazz, jint type, jobject args) {
    if (type == 0) {
        return detection_debug(env);
    }
    return NULL;
}

// Define JNI methods to be registered
static JNINativeMethod jniMethods[] = {
        {"callNative", "(ILjava/lang/Object;)Ljava/lang/Object;", (void *) entrance},
};

// Define JNI library registration function
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = nullptr;
    jint result = -1;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return result;
    }

    jclass clazz = env->FindClass("cn/tongdun/mobrisk/core/utils/JNIHelper");
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