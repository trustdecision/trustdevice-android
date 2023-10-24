package cn.tongdun.mobrisk.providers

/**
 * @description:MemoryInfoRawData
 * @author: wuzuchang
 * @date: 2023/5/16
 */
data class MemoryInfoRawData(
    val totalMemory: Long,
    val availableMemory: Long,
    val totalStorage: Long,
    val availableStorage: Long,
)