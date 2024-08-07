#include <jni.h>
#include "trustdevice_risk.h"
#include "trustdevice_utils.h"

// Define JNI methods to be registered
static JNINativeMethod jniMethods[] = {
        {"call0", "()I", (void *) detect_debug},
        {"call1", "()Ljava/lang/String;", (void *) detect_hook},
        {"call2", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", (void *) get_property},
        {"call3", "()Z", (void *) detectMagiskByMount},
        {"call4", "(Ljava/lang/String;)Z", (void *) checkBinInEnv}
};

// Define JNI library registration function
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *__unused) {
    JNIEnv *env = nullptr;
    jclass clazz = NULL;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        goto err_exit;
    }

     clazz = env->FindClass("cn/tongdun/mobrisk/core/tools/JNIHelper");

    if (clazz == nullptr) {
        goto err_exit;
    }
    if (env->RegisterNatives(clazz, jniMethods, sizeof(jniMethods) / sizeof(jniMethods[0])) < 0) {
        goto err_exit;
    }
    LOGD("JNI_OnLoad called!");

    return JNI_VERSION_1_6;
    err_exit:
    return JNI_ERR;
}