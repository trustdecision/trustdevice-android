//
// Created by zuchangwu on 2023/4/10.
//

#ifndef TRUSTDEVICE_ANDROID_DETECTION_RISK_H
#define TRUSTDEVICE_ANDROID_DETECTION_RISK_H

#include <jni.h>

#ifdef __LP64__
typedef unsigned long long operation_type;
static unsigned long trampoline_code = 0xd61f020058000050;
#else
typedef unsigned long operation_type;
static unsigned long trampoline_code = 0x58000050;
#endif
jint detect_debug(JNIEnv *env, jobject clazz);
jstring detect_hook(JNIEnv *env, jobject clazz);


#endif //TRUSTDEVICE_ANDROID_DETECTION_RISK_H
