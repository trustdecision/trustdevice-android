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

message("Current build type: ${CMAKE_BUILD_TYPE}")
if(CMAKE_BUILD_TYPE MATCHES Debug)
    # Debug build settings
    add_definitions(-DDEBUG)
    message("Open log & keep symbols")
else()
    # Release build settings
    add_definitions(-DNDEBUG)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fvisibility=hidden")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fvisibility=hidden")
    message("Close log & strip symbols")
endif()

target_link_libraries(
        trustdevice
        ${log-lib})