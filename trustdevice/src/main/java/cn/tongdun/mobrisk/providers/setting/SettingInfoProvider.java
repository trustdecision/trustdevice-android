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
        return mDeviceInfo.optString(Constants.KEY_ADB_ENABLED);
    }

    private String getDevelopmentSettingEnabled() {
        return mDeviceInfo.optString(Constants.KEY_DEVELOPMENT_SETTING_ENABLED);
    }

    private String getHttpProxy() {
        return mDeviceInfo.optString(Constants.KEY_HTTP_PROXY);
    }

    private String getDataRoaming() {
        return mDeviceInfo.optString(Constants.KEY_DATA_ROAMING);
    }

    private String getAllowMockLocation() {
        return mDeviceInfo.optString(Constants.KEY_ALLOW_MOCK_LOCATION);
    }

    private String getAccessibilityEnabled() {
        return mDeviceInfo.optString(Constants.KEY_ACCESSIBILITY_ENABLED);
    }

    private String getDefaultInputMethod() {
        return mDeviceInfo.optString(Constants.KEY_DEFAULT_INPUTMETHOD);
    }

    private String getTouchExplorationEnabled() {
        return mDeviceInfo.optString(Constants.KEY_TOUCH_EXPLORATION_ENABLED);
    }

    @Override
    public String getProviderName() {
        return "Setting info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new SettingInfoRawData(getAdbEnable(), getDevelopmentSettingEnabled(), getHttpProxy(), getDataRoaming(), getAllowMockLocation(), getAccessibilityEnabled(), getDefaultInputMethod(), getTouchExplorationEnabled()).loadData();
    }
}
