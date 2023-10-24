package cn.tongdun.mobrisk.providers

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/16
 */
data class SettingInfoRawData(
    val adb: String,
    val developmentSetting: String,
    val httpProxy: String,
    val dataRoaming: String,
    val allowMockLocation: String,
    val accessibility: String,
    val defaultInputMethod: String,
    val touchExplorationEnabled: String,
    val screenOffTimeout: String
)