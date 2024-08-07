cmake_minimum_required(VERSION 3.22.1)

project("trustdevice")

add_library(
        trustdevice

        SHARED

        trustdevice.cpp
        trustdevice_utils.cpp
        trustdevice_risk.cpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -Werror")

find_library(
        log-lib
        log)

if(CMAKE_BUILD_TYPE MATCHES Debug)
    # Debug build settings
    add_definitions(-DDEBUG)
elseif(CMAKE_BUILD_TYPE MATCHES Release)
    # Release build settings
    add_definitions(-DNDEBUG)
endif()

target_link_libraries(
        trustdevice
        ${log-lib})