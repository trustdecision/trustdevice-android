package cn.tongdun.mobrisk.core.collectors

import cn.tongdun.mobrisk.core.tools.JNIHelper
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface HookInterface {
    fun detectHook(): String
}

class HookCollector : HookInterface {
    override fun detectHook(): String = executeSafe({ JNIHelper.call1() }, "")
}