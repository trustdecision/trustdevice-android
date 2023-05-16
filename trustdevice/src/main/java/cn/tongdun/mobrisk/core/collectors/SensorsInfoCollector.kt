package cn.tongdun.mobrisk.core.collectors

import android.hardware.Sensor
import android.hardware.SensorManager
import cn.tongdun.mobrisk.core.tools.executeSafe
import org.json.JSONArray
import org.json.JSONObject

/**
 * @description:Sensors Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface SensorsInfoInterface {
    fun getSensorList(): String
}

class SensorsInfoCollector(private val sensorManager: SensorManager) : SensorsInfoInterface {
    override fun getSensorList(): String {
        val sensorArray = JSONArray()
        executeSafe {
            val sensorList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
            for (i in sensorList.indices) {
                val sensor = sensorList[i]
                val sensorInfo = JSONObject()
                sensorInfo.put("name", sensor.name)
                sensorInfo.put("vendor", sensor.vendor)
                sensorInfo.put("type", sensor.type)
                sensorArray.put(i, sensorInfo)
            }
        }
        return sensorArray.toString()
    }
}