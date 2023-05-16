package cn.tongdun.mobrisk.core.tools

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */

fun hash(algorithm: String, bytes: ByteArray): String {
    return executeSafe({
        val md = MessageDigest.getInstance(algorithm)
        md.update(bytes)
        StringUtils.byteToHexString(md.digest())
    }, "")
}

fun hash(algorithm: String, str: String): String {
    return executeSafe({
        val md = MessageDigest.getInstance(algorithm)
        md.update(str.toByteArray(StandardCharsets.UTF_8))
        StringUtils.byteToHexString(md.digest())
    }, "")
}