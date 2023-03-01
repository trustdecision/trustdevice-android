package cn.tongdun.mobrisk.providers.setting;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Setting Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class SettingInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;

    public SettingInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getAdbEnable() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_ADB_ENABLED);
    }

    private String getDevelopmentSettingEnabled() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DEVELOPMENT_SETTING_ENABLED);
    }

    private String getHttpProxy() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_HTTP_PROXY);
    }

    private String getDataRoaming() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DATA_ROAMING);
    }

    private String getAllowMockLocation() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_ALLOW_MOCK_LOCATION);
    }

    private String getAccessibilityEnabled() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_ACCESSIBILITY_ENABLED);
    }

    private String getDefaultInputMethod() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DEFAULT_INPUTMETHOD);
    }

    private String getTouchExplorationEnabled() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_TOUCH_EXPLORATION_ENABLED);
    }

    private int getScreenOffTimeout() {
        if (mDeviceInfo == null) {
            return 0;
        }
        return mDeviceInfo.optInt(Constants.KEY_SCREEN_OFF_TIMEOUT);
    }

    @Override
    public String getProviderName() {
        return "Setting info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new SettingInfoRawData(getAdbEnable(), getDevelopmentSettingEnabled(), getHttpProxy(), getDataRoaming(), getAllowMockLocation(), getAccessibilityEnabled(), getDefaultInputMethod(), getTouchExplorationEnabled(),getScreenOffTimeout()).loadData();
    }
}
