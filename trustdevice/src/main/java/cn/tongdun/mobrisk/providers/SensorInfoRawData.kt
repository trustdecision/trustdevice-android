package cn.tongdun.mobrisk.providers

import android.text.TextUtils
import cn.tongdun.mobrisk.core.tools.executeSafe
import org.json.JSONArray

/**
 * @description:SensorInfoRawData
 * @author: wuzuchang
 * @date: 2023/5/16
 */
data class SensorInfoRawData(val sensorInfo: String) {
    fun getSensorTotal(): Int {
        if (TextUtils.isEmpty(sensorInfo)) {
            return 0
        }
        var sensorArray: JSONArray? = null
        executeSafe { sensorArray = JSONArray(sensorInfo) }
        return sensorArray?.length() ?: 0
    }
}