package cn.tongdun.mobrisk.core.tools

import android.text.TextUtils
import java.io.File

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object EnvUtils {
    fun fileInEnv(fileName: String): Boolean {
        val envMap = System.getenv()
        val pathValue = envMap["PATH"]
        if (TextUtils.isEmpty(pathValue)) {
            return false
        }
        val paths = pathValue!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (path in paths) {
            val fullPath = path + File.separator + fileName
            val file = File(fullPath)
            if (file.exists()) {
                return true
            }
        }
        return false
    }
}