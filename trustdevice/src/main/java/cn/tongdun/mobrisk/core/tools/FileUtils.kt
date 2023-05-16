package cn.tongdun.mobrisk.core.tools

import java.io.*
import java.nio.charset.StandardCharsets

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object FileUtils {
    fun readFileByKey(fileName: String?, keys: List<String>?, sep: String?): Map<String, String> {
        val info: MutableMap<String, String> = HashMap()
        var br: BufferedReader? = null
        if (fileName != null && keys != null && File(fileName).exists()) {
            try {
                br = BufferedReader(
                    InputStreamReader(
                        FileInputStream(fileName),
                        StandardCharsets.UTF_8
                    )
                )
                var raw: String
                while (br.readLine().also { raw = it } != null) {
                    var paramValue = ""
                    if (sep?.isNotEmpty() == true) {
                        var paramName = ""
                        val params: List<String> = StringUtils.splitNonRegex(raw, sep)
                        if (params.isNotEmpty() && params[0].trim { it <= ' ' }.also {
                                paramName = it
                            }.isNotEmpty()) {
                            if (keys.contains(paramName)) {
                                if (params.size > 1) {
                                    paramValue = params[1].trim { it <= ' ' }
                                    info[paramName] = paramValue
                                }
                            }
                        }
                    } else {
                        for (k in keys) {
                            if (raw.contains(k)) {
                                info[k] = raw
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            } finally {
                if (br != null) {
                    executeSafe { br.close() }
                }
            }
        }
        return info
    }
}