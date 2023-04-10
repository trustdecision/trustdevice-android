//
// Created by zuchangwu on 2023/4/10.
//

#include "detection_risk.h"
#include <iostream>
#include <fstream>
#include <string>
#include <unistd.h>
#include "utils.h"

extern "C" JNIEXPORT int JNICALL readTracePid(const char * file_path){
    int tracerPid = 0;
    std::ifstream status_file(file_path);
    if (!status_file) {
        return tracerPid;
    }
    std::string line;
    while (std::getline(status_file, line)) {
        if (line.find("TracerPid:") == 0) {
            tracerPid = std::stoi(line.substr(11));
            break;
        }
    }
    status_file.close();
    return tracerPid;
}

extern "C" JNIEXPORT bool JNICALL detectTracePid() {
    const char* status_path = "/proc/self/status";
    int tracerPid = readTracePid(status_path);
    if (tracerPid != 0) {
        return true;
    }
    return false;
}

extern "C" JNIEXPORT bool JNICALL detectTaskTracerPid() {
    pid_t pid = getpid();
    char buffer[512];
    const char *format = "/proc/%d/task/%d/status";
    std::sprintf(buffer, format, pid, pid);
    int tracerPid = readTracePid(buffer);
    if (tracerPid != 0) {
        return true;
    }
    return false;
}

extern "C" JNIEXPORT jint JNICALL detect_debug(JNIEnv *env, jclass clazz) {
    int result = 0;
    if (detectTracePid()) {
        result |= 0x1 << 1;
    }
    if (detectTaskTracerPid()) {
        result |= 0x1 << 2;
    }
    return static_cast<jint>(result);;
}