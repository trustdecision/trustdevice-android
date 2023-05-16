package cn.tongdun.mobrisk.core.collectors

import android.os.Build
import android.text.TextUtils
import cn.tongdun.mobrisk.core.tools.FileUtils
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description:Cpu Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */

interface CpuInfoInterface {

    fun getCpuProcessor(): String

    fun getCpuHardware(): String

    fun getAbiType(): String

    fun availableProcessors(): String
}

private val keyList = listOf("Processor", "Hardware")
private const val CPU_INFO_PATH = "/proc/cpuinfo"
private const val KEY_VALUE_DELIMITER = ": "
private var mCpuInfo: Map<String, String> = mutableMapOf()

class CpuInfoCollector : CpuInfoInterface {

    init {
        executeSafe {
            mCpuInfo = FileUtils.readFileByKey(CPU_INFO_PATH, keyList, KEY_VALUE_DELIMITER)
        }
    }

    override fun getCpuProcessor(): String = executeSafe({ mCpuInfo[keyList[0]] ?: "" }, "")

    override fun getCpuHardware(): String = executeSafe({ mCpuInfo[keyList[1]] ?: "" }, "")

    override fun getAbiType(): String =
        executeSafe({ TextUtils.join(",", Build.SUPPORTED_ABIS) }, "")

    override fun availableProcessors(): String =
        executeSafe({ Runtime.getRuntime().availableProcessors().toString() }, "")
}