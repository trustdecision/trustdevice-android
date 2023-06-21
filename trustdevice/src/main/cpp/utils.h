//
// Created by zuchangwu on 2023/4/7.
//

#ifndef TRUSTDEVICE_ANDROID_UTILS_H
#define TRUSTDEVICE_ANDROID_UTILS_H

jstring get_property(JNIEnv *env, jobject clazz, jstring _key, jstring _default_value);

extern "C" JNIEXPORT int JNICALL mem_read_access_by_maps(void *read_addr, size_t len);
#endif //TRUSTDEVICE_ANDROID_UTILS_H
