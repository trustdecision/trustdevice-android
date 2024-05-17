//
// Created by zuchangwu on 2023/4/10.
//

#ifndef TRUSTDEVICE_ANDROID_DETECTION_RISK_H
#define TRUSTDEVICE_ANDROID_DETECTION_RISK_H

#include <jni.h>
#include <iostream>
#include <fstream>
#include <string>
#include <unistd.h>
#include <fcntl.h>
#include <dlfcn.h>
#include <sys/stat.h>

#include "utils.h"
#include "common/macro.h"
#include "common/logger.h"

jint detect_debug(JNIEnv *env, jobject clazz);
jstring detect_hook(JNIEnv *env, jobject clazz);
jboolean detectMagiskByMount(JNIEnv *env, jobject __unused);

#endif //TRUSTDEVICE_ANDROID_DETECTION_RISK_H
