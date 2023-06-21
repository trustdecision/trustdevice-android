//
// Created by zuchangwu on 2023/4/7.
//

#include "utils.h"

jstring get_property(JNIEnv *env, jobject __unused, jstring _key, jstring _default_value) {
    const char *c_key = env->GetStringUTFChars(_key, nullptr);
    char value[128] = "";
    int length = __system_property_get(c_key, value);
    if (length < 0) {
        return _default_value;
    }
    return env->NewStringUTF(value);
}

/**
 * Parse every step of the map, observe the permission part, it is no permission
 * 0: not accessible; 1: accessibles
 */
extern "C" JNIEXPORT int JNICALL mem_read_access_by_maps(void *read_addr, size_t len) {
    if(read_addr == nullptr)
        return 0;
#if defined(__aarch64__)
    uint64_t addr = (uint64_t)read_addr;
    uint64_t start_addr = 0, end_addr = 0, last_addr = 0;
#else
    uint32_t addr = (uint32_t)read_addr;
    uint32_t start_addr = 0, end_addr = 0, last_addr = 0;
#endif
    char access = 0, buff[1024];
    FILE *fmap;
    fmap = fopen("/proc/self/maps", "r");
    if(!fmap){
        LOGD("open /proc/self/maps file failed!/n");
        return 0;
    }

    while(fgets(buff, sizeof(buff)-1, fmap) != nullptr) {
#if defined(__aarch64__)
        sscanf(buff, "%lx-%lx %c", &start_addr, &end_addr, &access);
#else
        sscanf(buff, "%x-%x %c", &start_addr, &end_addr, &access);
#endif
        if((addr >= start_addr) && (addr <= end_addr)){
            if('r' != access){
                fclose(fmap);
                return 0;
            }

            if((addr + len) < end_addr){
                fclose(fmap);
                return 1;
            }else {
                last_addr = end_addr;
                len = len - (end_addr - addr);
                addr = last_addr;
            }
        }
    }

    fclose(fmap);
    return 0;
}