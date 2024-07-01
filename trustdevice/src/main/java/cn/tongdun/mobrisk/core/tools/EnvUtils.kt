package cn.tongdun.mobrisk.core.tools

import android.text.TextUtils
import java.io.File

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object EnvUtils {
    fun fileInEnv(fileName: String) = JNIHelper.call4(fileName)
}