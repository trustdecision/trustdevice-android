package cn.tongdun.mobrisk.providers

/**
 * @description:BatteryInfoRawData
 * @author: wuzuchang
 * @date: 2023/5/15
 */
data class BatteryInfoRawData(
    val health: String,
    val status: String,
    val temp: Int,
    val level: Int,
    val TotalCapacity: String
)