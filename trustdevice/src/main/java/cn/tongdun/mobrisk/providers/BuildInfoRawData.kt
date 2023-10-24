package cn.tongdun.mobrisk.providers

import android.util.Pair

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */
data class BuildInfoRawData(
    val model: String,
    val brand: String,
    val manufacturer: String,
    val androidVersion: String,
    val sdkVersion: String,
    val kernelVersion: String,
    val fingerprint: String,
    val host: String,
    val screenResolution: String,
    val screenInches: String,
    val screenBrightness: String,
    val screenOffTimeout: String
) {
    fun getRawData(): List<Pair<String, String>> = mutableListOf<Pair<String, String>>().apply {
        add(Pair<String, String>("model", model))
        add(Pair<String, String>("brand", brand))
        add(Pair<String, String>("manufacturer", manufacturer))
        add(Pair<String, String>("android version", androidVersion))
        add(Pair<String, String>("sdk version", sdkVersion))
        add(Pair<String, String>("kernel version", kernelVersion))
        add(Pair<String, String>("fingerprint", fingerprint))
        add(Pair<String, String>("host", host))
        add(Pair<String, String>("screen resolution", screenResolution))
        add(Pair<String, String>("screen inches", screenInches))
        add(Pair<String, String>("screen brightness", screenBrightness))
    }
}