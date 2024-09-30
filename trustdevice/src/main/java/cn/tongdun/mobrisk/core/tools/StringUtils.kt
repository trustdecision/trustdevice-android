package cn.tongdun.mobrisk.core.tools

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */

fun String.hash(algorithm: String): String {
    return executeSafe({
        val md = MessageDigest.getInstance(algorithm)
        md.update(this.toByteArray(StandardCharsets.UTF_8))
        md.digest().toUppercaseHexString()
    }, "")
}

fun String.splitNonRegex(delimiter: String): List<String> {
    val list: MutableList<String> = mutableListOf()
    var input = this
    if (input.isEmpty()) {
        return list
    }
    if (delimiter.isEmpty()) {
        list.add(input)
        return list
    }
    if (input == delimiter) {
        return list
    }
    while (true) {
        val index: Int = input.indexOf(delimiter)
        if (index == -1) {
            if (input.isNotEmpty()) {
                list.add(input)
            }
            return list
        }
        if (index != 0) {
            list.add(input.substring(0, index))
        }
        input = input.substring(index + delimiter.length)
    }
}

fun ByteArray.hash(algorithm: String): String {
    return executeSafe({
        val md = MessageDigest.getInstance(algorithm)
        md.update(this)
        md.digest().toUppercaseHexString()
    }, "")
}

fun ByteArray.toUppercaseHexString(): String {
    val hex = StringBuilder()
    // Iterating through each byte in the array
    for (i in this) {
        hex.append(String.format("%02X", i))
    }
    return hex.toString()
}