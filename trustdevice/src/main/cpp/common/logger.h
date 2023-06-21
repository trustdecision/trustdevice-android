//
// Created by collam on 2023/6/21.
//

#ifndef TRUSTDEVICE_ANDROID_LOGGER_H
#define TRUSTDEVICE_ANDROID_LOGGER_H

#include <android/log.h>

#define TAG "TrustDevice_native"

#ifdef DEBUG
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__)
#else
#define LOGI(...)
#define LOGV(...)
#define LOGD(...)
#define LOGW(...)
#endif

#endif //TRUSTDEVICE_ANDROID_LOGGER_H
