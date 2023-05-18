package cn.tongdun.mobrisk.core.tools

import java.io.*
import java.nio.charset.StandardCharsets

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object FileUtils {
    fun readFileByKey(fileName: String, keys: List<String>, sep: String): Map<String, String> {
        val info: MutableMap<String, String> = mutableMapOf()
        val file = File(fileName)
        if (!file.exists()) {
            return info
        }
        file.readLines().forEach { line->
            if (line.isEmpty()) {
                return@forEach
            }
            val params: List<String> = StringUtils.splitNonRegex(line, sep)
            var paramName = ""
            if (params.isNotEmpty() && params[0].trim().also {
                    paramName = it
                }.isNotEmpty())  {
                if (keys.contains(paramName)) {
                    if (params.size > 1) {
                        info[paramName] = params[1].trim()
                    }
                }
            }
        }
        return info
    }
}