package cn.tongdun.mobrisk.providers.screen;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Screen info Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class ScreenInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;
    public ScreenInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getScreenResolution() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SCREEN_RESOLUTION);
    }

    private String getScreenBrightness() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SCREEN_BRIGHTNESS);
    }

    private String getScreenOffTimeout() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SCREEN_OFF_TIMEOUT);
    }

    @Override
    public String getProviderName() {
        return "Screen info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new ScreenInfoRawData(getScreenResolution(), getScreenBrightness(), getScreenOffTimeout()).loadData();
    }
}
