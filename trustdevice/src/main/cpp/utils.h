//
// Created by 吴祖昌 on 2023/4/7.
//

#ifndef TRUSTDEVICE_ANDROID_UTILS_H
#define TRUSTDEVICE_ANDROID_UTILS_H

#include <android/log.h>
#define TAG "TrustDevice"

#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__)

class utils {
};


#endif //TRUSTDEVICE_ANDROID_UTILS_H
