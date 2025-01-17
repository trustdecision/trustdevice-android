package cn.tongdun.mobrisk.core.tools

import cn.tongdun.mobrisk.beans.DeviceInfo
import cn.tongdun.mobrisk.beans.DisplayName
import org.json.JSONObject
import java.io.File

/**
 * @description: DeviceInfoUtils
 * @author: wuzuchang
 * @date: 2023/5/12
 */

object DeviceInfoUtils {
    fun format(deviceId: String, deviceInfo: DeviceInfo): JSONObject {
        val result = JSONObject()
        val detail = JSONObject()
        val fields = deviceInfo.javaClass.declaredFields
        for (field in fields) {
            executeSafe {
                field.isAccessible = true
                var key = field.name
                for (annotation in field.declaredAnnotations) {
                    if(annotation is DisplayName) {
                        key = annotation.value
                        break
                    }
                }
                val value = field.get(deviceInfo)
                detail.put(key, value?.toString())
            }
        }
        executeSafe {
            result.put(Constants.KEY_DEVICE_ID, deviceId)
            result.put(Constants.KEY_DEVICE_RISK_LABEL, deviceRisk(detail))
            result.put(Constants.KEY_DEVICE_DETAIL, detail)
        }
        return result
    }

    private fun deviceRisk(data: JSONObject): JSONObject {
        val risk = JSONObject()
        executeSafe {
            risk.put(Constants.KEY_ROOT, data.optString(Constants.KEY_ROOT))
            risk.put(Constants.KEY_DEBUG, (data.optInt(Constants.KEY_DEBUG)>0).toString())
            risk.put(Constants.KEY_MULTIPLE, getMultiple(data))
            risk.put(Constants.KEY_XPOSED, data.optString(Constants.KEY_XPOSED))
            risk.put(Constants.KEY_MAGISK, data.optString(Constants.KEY_MAGISK))
            risk.put(Constants.KEY_HOOK, getHookStatus(data))
            risk.put(Constants.KEY_EMULATOR, getEmulatorStatus(data))
            risk.put(Constants.KEY_VPN, getVpnStatus(data))
            risk.put(Constants.KEY_DEVICE_INFO_TAMPERED, getDeviceInfoTamperedStatus(data))
        }
        return risk
    }

    private fun getMultiple(data: JSONObject): String {
        val filePath = data.optString(Constants.KEY_FILES_ABSOLUTE_PATH)
        val packageName = data.optString(Constants.KEY_PACKAGE_NAME)
        val filePaths = filePath.split(packageName.toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        if (filePaths.isEmpty()) {
            return "false"
        }
        val dir = filePaths[0]
        var riskLength = 3
        if (dir.startsWith(File.separator)) {
            riskLength = 4
        }
        val dirs = dir.split(File.separator.toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        if (dirs.size > riskLength) {
            return "true"
        }
        return if ("0" != dirs[dirs.size - 1]) {
            "true"
        } else "false"
    }

    private fun getHookStatus(data: JSONObject): String {
        return if (data.optString(Constants.KEY_HOOK).isEmpty()) "false" else "true"
    }

    private fun getEmulatorStatus(data: JSONObject): String {
        return data.optString(Constants.KEY_EMULATOR)
    }

    private fun getVpnStatus(data: JSONObject): String {
        return data.optString(Constants.KEY_VPN)
    }

    private fun getDeviceInfoTamperedStatus(data: JSONObject): String {
        return data.optString(Constants.KEY_DEVICE_INFO_TAMPERED)
    }
}
