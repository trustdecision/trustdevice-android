package cn.tongdun.mobrisk.providers

/**
 * @description:DeviceIdRawData
 * @author: wuzuchang
 * @date: 2023/5/16
 */
data class DeviceIdRawData(
    val deviceId: String,
    val androidId: String,
    val gsfId: String,
    val gadId: String,
    val mediaDrmId: String,
    val vbMetaDigest: String
)