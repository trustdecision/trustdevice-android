package cn.tongdun.mobrisk.core.collectors

import cn.tongdun.mobrisk.core.tools.EnvUtils
import cn.tongdun.mobrisk.core.tools.JNIHelper

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface MagiskInterface {
    fun detectMagisk(): Boolean
}

class MagiskCollector : MagiskInterface {

    override fun detectMagisk(): Boolean {
        return detectMagiskByFile() || detectMagiskByLastModified()
    }

    private fun detectMagiskByLastModified(): Boolean {
        return JNIHelper.call3()
    }

    private fun detectMagiskByFile(): Boolean {
        return EnvUtils.fileInEnv("magisk")
    }
}