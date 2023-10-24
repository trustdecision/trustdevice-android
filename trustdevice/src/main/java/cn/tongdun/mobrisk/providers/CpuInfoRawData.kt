package cn.tongdun.mobrisk.providers

import android.util.Pair

/**
 * @description:CpuInfoRawData
 * @author: wuzuchang
 * @date: 2023/5/15
 */
data class CpuInfoRawData(
    val cpuHardware: String,
    val cpuProcessor: String,
    val abiType: String,
    val coresCount: String
) {
    fun getRawData(): List<Pair<String, String>> = mutableListOf<Pair<String, String>>().apply {
        add(Pair<String, String>("hardware", cpuHardware))
        add(Pair<String, String>("processor", cpuProcessor))
        add(Pair<String, String>("abi type", abiType))
        add(Pair<String, String>("cores count", coresCount))
    }
}
