//
// Created by collam on 2023/6/21.
//

#ifndef TRUSTDEVICE_ANDROID_LOGGER_H
#define TRUSTDEVICE_ANDROID_LOGGER_H

#include <android/log.h>
#include "../utils.h"

#define TAG "TrustDevice_native"

#ifdef DEBUG

#define LOG(_level,...) do { \
        char logBuffer[4096];                                              \
        snprintf(logBuffer, sizeof(logBuffer), __VA_ARGS__);            \
        __android_log_print(_level,TAG,"[%s] %s",get_current_thread_name(),logBuffer);                            \
} while(false)

#define LOGI(...) LOG(ANDROID_LOG_INFO,__VA_ARGS__)
#define LOGV(...) LOG(ANDROID_LOG_VERBOSE,__VA_ARGS__)
#define LOGD(...) LOG(ANDROID_LOG_DEBUG, __VA_ARGS__)
#define LOGW(...) LOG(ANDROID_LOG_WARN,__VA_ARGS__)
#else
#define LOGI(...)
#define LOGV(...)
#define LOGD(...)
#define LOGW(...)
#endif

#endif //TRUSTDEVICE_ANDROID_LOGGER_H
