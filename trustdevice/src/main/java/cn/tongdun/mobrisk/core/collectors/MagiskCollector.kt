package cn.tongdun.mobrisk.core.collectors

import cn.tongdun.mobrisk.core.tools.Constants
import cn.tongdun.mobrisk.core.tools.EnvUtils
import java.io.File

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
        val mountsFile: File = File(Constants.MAGISK_MOUNTS_PATH)
        val mountInfoFile: File = File(Constants.MAGISK_MOUNT_INFO_PATH)
        val mountStatsFile: File = File(Constants.MAGISK_MOUNT_STATS_PATH)
        return !(mountsFile.lastModified() == mountInfoFile.lastModified()
                && mountInfoFile.lastModified() == mountStatsFile.lastModified())
    }

    private fun detectMagiskByFile(): Boolean {
        return EnvUtils.fileInEnv("magisk")
    }
}