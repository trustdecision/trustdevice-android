//
// Created by zuchangwu on 2023/4/7.
//

#ifndef TRUSTDEVICE_ANDROID_UTILS_H
#define TRUSTDEVICE_ANDROID_UTILS_H

#include <jni.h>
#include <string>
#include <sys/prctl.h>
#include "common/logger.h"

jstring get_property(JNIEnv *env, jobject clazz, jstring _key, jstring _default_value);
bool mem_read_access_by_maps(void *read_addr, size_t len);
char *get_current_thread_name();

#endif //TRUSTDEVICE_ANDROID_UTILS_H
