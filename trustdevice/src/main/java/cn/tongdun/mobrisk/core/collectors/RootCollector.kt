package cn.tongdun.mobrisk.core.collectors

import cn.tongdun.mobrisk.core.tools.EnvUtils

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */

interface RootInterface {
    fun getRoot(): Boolean
}

class RootCollector : RootInterface {
    override fun getRoot(): Boolean = EnvUtils.fileInEnv("su")
}