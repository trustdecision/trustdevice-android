package cn.tongdun.mobrisk.providers

import cn.tongdun.mobrisk.core.tools.Constants
import org.json.JSONObject

/**
 * @description:DeviceInfoProvider
 * @author: wuzuchang
 * @date: 2023/5/15
 */

class DeviceInfoProvider(private val deviceJson: JSONObject) {
    private var deviceDetail: JSONObject =
        deviceJson.optJSONObject(Constants.KEY_DEVICE_DETAIL) ?: JSONObject()
    private var riskLabel: JSONObject =
        deviceJson.optJSONObject(Constants.KEY_DEVICE_RISK_LABEL) ?: JSONObject()

    fun getDeviceInfo(): DeviceIdRawData = DeviceIdRawData(
        deviceJson.optString(Constants.KEY_DEVICE_ID),
        deviceDetail.optString(Constants.KEY_ANDROID_ID),
        deviceDetail.optString(Constants.KEY_GSF_ID),
        deviceDetail.optString(Constants.KEY_GAD_ID),
        deviceDetail.optString(Constants.KEY_MEDIA_DRM_ID),
        deviceDetail.optString(Constants.KEY_VB_META_DIGEST)
    )

    fun getRiskInfo(): RiskInfoRawData = RiskInfoRawData(
        riskLabel.optBoolean(Constants.KEY_ROOT),
        riskLabel.optBoolean(Constants.KEY_DEBUG),
        riskLabel.optBoolean(Constants.KEY_MULTIPLE),
        riskLabel.optBoolean(Constants.KEY_XPOSED),
        riskLabel.optBoolean(Constants.KEY_MAGISK),
        riskLabel.optBoolean(Constants.KEY_HOOK),
        riskLabel.optBoolean(Constants.KEY_EMULATOR),
    )

    fun getAppListInfo(): AppListsRawData = AppListsRawData(
        deviceDetail.optString(Constants.KEY_SYSTEM_APP_LIST),
        deviceDetail.optString(Constants.KEY_APP_LIST)
    )

    fun getBatteryInfo(): BatteryInfoRawData = BatteryInfoRawData(
        deviceDetail.optString(Constants.KEY_BATTERY_HEALTH_STATUS),
        deviceDetail.optString(Constants.KEY_BATTERY_STATUS),
        deviceDetail.optInt(Constants.KEY_BATTERY_TEMP),
        deviceDetail.optInt(Constants.KEY_BATTERY_LEVEL),
        deviceDetail.optString(Constants.KEY_BATTERY_TOTAL_CAPACITY)
    )

    fun getBuildInfo(): BuildInfoRawData = BuildInfoRawData(
        deviceDetail.optString(Constants.KEY_MODEL),
        deviceDetail.optString(Constants.KEY_BRAND),
        deviceDetail.optString(Constants.KEY_MANUFACTURER),
        deviceDetail.optString(Constants.KEY_ANDROID_VERSION),
        deviceDetail.optString(Constants.KEY_SDK_VERSION),
        deviceDetail.optString(Constants.KEY_KERNEL_VERSION),
        deviceDetail.optString(Constants.KEY_FINGERPRINT),
        deviceDetail.optString(Constants.KEY_HOST),
        deviceDetail.optString(Constants.KEY_SCREEN_RESOLUTION),
        deviceDetail.optString(Constants.KEY_SCREEN_INCHES),
        deviceDetail.optString(Constants.KEY_SCREEN_BRIGHTNESS),
        deviceDetail.optString(Constants.KEY_SCREEN_OFF_TIMEOUT)
    )

    fun getCpuInfo(): CpuInfoRawData = CpuInfoRawData(
        deviceDetail.optString(Constants.KEY_CPU_HARDWARE),
        deviceDetail.optString(Constants.KEY_CPU_PROCESSOR),
        deviceDetail.optString(Constants.KEY_ABI_TYPE),
        deviceDetail.optString(Constants.KEY_CORES_COUNT)
    )

    fun getMemoryInfo(): MemoryInfoRawData = MemoryInfoRawData(
        deviceDetail.optLong(Constants.KEY_TOTAL_MEMORY),
        deviceDetail.optLong(Constants.KEY_AVAILABLE_MEMORY),
        deviceDetail.optLong(Constants.KEY_TOTAL_STORAGE),
        deviceDetail.optLong(Constants.KEY_AVAILABLE_STORAGE)
    )

    fun getSensorInfo(): SensorInfoRawData =
        SensorInfoRawData(deviceDetail.optString(Constants.KEY_SENSORS_INFO))

    fun getSettingInfo(): SettingInfoRawData = SettingInfoRawData(
        deviceDetail.optString(Constants.KEY_ADB_ENABLED),
        deviceDetail.optString(Constants.KEY_DEVELOPMENT_SETTING_ENABLED),
        deviceDetail.optString(Constants.KEY_HTTP_PROXY),
        deviceDetail.optString(Constants.KEY_DATA_ROAMING),
        deviceDetail.optString(Constants.KEY_ALLOW_MOCK_LOCATION),
        deviceDetail.optString(Constants.KEY_ACCESSIBILITY_ENABLED),
        deviceDetail.optString(Constants.KEY_DEFAULT_INPUTMETHOD),
        deviceDetail.optString(Constants.KEY_TOUCH_EXPLORATION_ENABLED),
        deviceDetail.optString(Constants.KEY_SCREEN_OFF_TIMEOUT)
    )
}