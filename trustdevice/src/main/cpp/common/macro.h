//
// Created by collam on 2023/6/21.
//

#ifndef TRUSTDEVICE_ANDROID_MACRO_H
#define TRUSTDEVICE_ANDROID_MACRO_H

#ifdef __LP64__
typedef unsigned long long operation_type;
static unsigned long trampoline_code = 0xd61f020058000050;
#else
typedef unsigned long operation_type;
static unsigned long trampoline_code = 0x58000050;
#endif

#endif //TRUSTDEVICE_ANDROID_MACRO_H
