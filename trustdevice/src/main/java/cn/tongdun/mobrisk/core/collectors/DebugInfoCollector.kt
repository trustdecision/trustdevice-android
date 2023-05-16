package cn.tongdun.mobrisk.core.collectors

import android.os.Debug
import cn.tongdun.mobrisk.core.tools.JNIHelper

/**
 * @description:Debug info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface DebugInfoInterface {
    fun getDebug(): Int
}

class DebugInfoCollector : DebugInfoInterface {
    override fun getDebug(): Int {
        var result = 0
        if (Debug.isDebuggerConnected()) {
            result = result or 0x1
        }
        val ret: Int = JNIHelper.detectDebug()
        result = result or ret
        return result
    }
}