package cn.tongdun.mobrisk.core.tools

import java.lang.Exception

fun <T> executeSafe(code: () -> T) {
    try {
        code()
    } catch (_: Exception) {
    }
}

fun <T> executeSafe(code: () -> T, defaultValue: T): T {
    return try {
        code()
    } catch (exception: Exception) {
        defaultValue
    }
}