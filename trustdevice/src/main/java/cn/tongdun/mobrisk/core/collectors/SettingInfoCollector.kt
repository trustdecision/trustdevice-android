package cn.tongdun.mobrisk.core.collectors

import android.content.ContentResolver
import android.provider.Settings
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description:Setting Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface SettingInfoInterface {
    fun getAdbEnabled(): String
    fun getDevelopmentSettingEnabled(): String
    fun getHttpProxy(): String
    fun getDataRoaming(): String
    fun getAllowMockLocation(): String
    fun getAccessibilityEnabled(): String
    fun getDefaultInputMethod(): String
    fun getTouchExplorationEnabled(): String
    fun getScreenBrightness(): String
    fun getScreenOffTimeout(): String
}

class SettingInfoCollector(private val contentResolver: ContentResolver) : SettingInfoInterface {

    override fun getAdbEnabled(): String = extractGlobalSettingsParam(Settings.Global.ADB_ENABLED)

    override fun getDevelopmentSettingEnabled(): String =
        extractGlobalSettingsParam(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED)

    override fun getHttpProxy(): String = extractGlobalSettingsParam(Settings.Global.HTTP_PROXY)

    override fun getDataRoaming(): String = extractGlobalSettingsParam(Settings.Global.DATA_ROAMING)

    override fun getAllowMockLocation(): String =
        extractSecureSettingsParam(Settings.Secure.ALLOW_MOCK_LOCATION)

    override fun getAccessibilityEnabled(): String =
        extractSecureSettingsParam(Settings.Secure.ACCESSIBILITY_ENABLED)

    override fun getDefaultInputMethod(): String =
        extractSecureSettingsParam(Settings.Secure.DEFAULT_INPUT_METHOD)

    override fun getTouchExplorationEnabled(): String =
        extractSecureSettingsParam(Settings.Secure.TOUCH_EXPLORATION_ENABLED)

    override fun getScreenBrightness(): String =
        extractSystemSettingsParam(Settings.System.SCREEN_BRIGHTNESS)

    override fun getScreenOffTimeout(): String =
        extractSystemSettingsParam(Settings.System.SCREEN_OFF_TIMEOUT)

    private fun extractGlobalSettingsParam(key: String): String =
        executeSafe({ Settings.Global.getString(contentResolver, key) }, "")

    private fun extractSecureSettingsParam(key: String): String =
        executeSafe({ Settings.Secure.getString(contentResolver, key) }, "")

    private fun extractSystemSettingsParam(key: String): String =
        executeSafe({ Settings.System.getString(contentResolver, key) }, "")

}