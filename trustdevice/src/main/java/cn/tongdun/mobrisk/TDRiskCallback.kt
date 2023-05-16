package cn.tongdun.mobrisk

import org.json.JSONObject

/**
 * @description: TDRisk Callback
 * @author: wuzuchang
 * @date: 2023/5/12
 */
interface TDRiskCallback {
    fun onEvent(deviceInfo: JSONObject)
}