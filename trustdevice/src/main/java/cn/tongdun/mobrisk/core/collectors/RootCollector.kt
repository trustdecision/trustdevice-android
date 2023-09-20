package cn.tongdun.mobrisk.core.collectors

import cn.tongdun.mobrisk.core.tools.EnvUtils
import cn.tongdun.mobrisk.core.tools.JNIHelper

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */

interface RootInterface {
    fun getRoot(): Boolean
}

class RootCollector : RootInterface {
    override fun getRoot(): Boolean  {
        return EnvUtils.fileInEnv("su") || checkProp()
    }

    private fun checkProp() = JNIHelper.call2("ro.secure","1") == "0"
}