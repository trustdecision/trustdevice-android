//
// Created by zuchangwu on 2023/4/7.
//

#include <jni.h>
#include <string>
#include "utils.h"

extern "C" JNIEXPORT jstring JNICALL
get_property(JNIEnv *env, jobject clazz, jstring _key, jstring _default_value) {
    const char *c_key = env->GetStringUTFChars(_key, nullptr);
    char value[128] = "";
    int length = __system_property_get(c_key, value);
    if (length < 0) {
        return _default_value;
    }
    return env->NewStringUTF(value);
}