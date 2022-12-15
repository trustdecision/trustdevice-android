package cn.tongdun.mobrisk.core.collectors;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @description: DevicePersonalizationInfoCollector
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DevicePersonalizationInfoCollector {

    public DevicePersonalizationInfoCollector() {
    }

    public String getLocaleCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getTimezone() {
        return TimeZone.getDefault().getDisplayName();
    }
}
