package cn.tongdun.mobrisk.core.collectors

import java.util.*

/**
 * @description:Device Personalization Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface DevicePersonalizationInfoInterface {
    fun getLocaleCountry(): String?
    fun getDefaultLanguage(): String?
    fun getTimezone(): String?
}

class DevicePersonalizationInfoCollector : DevicePersonalizationInfoInterface {
    override fun getLocaleCountry(): String? {
        return Locale.getDefault().country
    }

    override fun getDefaultLanguage(): String? {
        return Locale.getDefault().language
    }

    override fun getTimezone(): String? {
        return TimeZone.getDefault().displayName
    }
}