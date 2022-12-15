package cn.tongdun.mobrisk.core.collectors;

import android.content.ContentResolver;
import android.provider.Settings;

/**
 * @description: Setting Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class SettingInfoCollector {
    private ContentResolver mContentResolver;

    public SettingInfoCollector(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    public String getAdbEnabled() {
        return extractGlobalSettingsParam(Settings.Global.ADB_ENABLED);
    }

    public String getDevelopmentSettingEnabled() {
        return extractGlobalSettingsParam(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED);
    }

    public String getHttpProxy() {
        return extractGlobalSettingsParam(Settings.Global.HTTP_PROXY);
    }

    public String getDataRoaming() {
        return extractGlobalSettingsParam(Settings.Global.DATA_ROAMING);
    }

    public String getAllowMockLocation() {
        return extractSecureSettingsParam(Settings.Secure.ALLOW_MOCK_LOCATION);
    }

    public String getAccessibilityEnabled() {
        return extractSecureSettingsParam(Settings.Secure.ACCESSIBILITY_ENABLED);
    }

    public String getDefaultInputMethod() {
        return extractSecureSettingsParam(Settings.Secure.DEFAULT_INPUT_METHOD);
    }

    public String getTouchExplorationEnabled() {
        return extractSecureSettingsParam(Settings.Secure.TOUCH_EXPLORATION_ENABLED);
    }

    public String getScreenBrightness() {
        return extractSystemSettingsParam(Settings.System.SCREEN_BRIGHTNESS);
    }

    public String getScreenOffTimeout() {
        return extractSystemSettingsParam(Settings.System.SCREEN_OFF_TIMEOUT);
    }

    public String extractGlobalSettingsParam(String key) {
        try {
            return Settings.Global.getString(mContentResolver, key);
        } catch (Exception ignored) {
        }
        return "";
    }

    public String extractSecureSettingsParam(String key) {
        try {
            return Settings.Secure.getString(mContentResolver, key);
        } catch (Exception ignored) {
        }
        return "";
    }

    public String extractSystemSettingsParam(String key) {
        try {
            return Settings.System.getString(mContentResolver, key);
        } catch (Exception ignored) {
        }
        return "";
    }
}
