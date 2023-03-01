package cn.tongdun.mobrisk.providers.build;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;

/**
 * @description: BuildInfo Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BuildInfoProvider {
    private final JSONObject mDeviceInfo;

    public BuildInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    public String getModel() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_MODEL);
    }

    public String getBrand() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BRAND);
    }

    private String getManufacturer() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_MANUFACTURER);
    }

    private String getAndroidVersion() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_ANDROID_VERSION);
    }

    private String getSdkVersion() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SDK_VERSION);
    }

    private String getKernelVersion() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_KERNEL_VERSION);
    }

    private String getFingerprint() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_FINGERPRINT);
    }

    private String getHost() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_HOST);
    }


    private String getScreenResolution() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SCREEN_RESOLUTION);
    }

    private String getScreenInches() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SCREEN_INCHES);
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

    public List<Pair<String, String>> getRawData() {
        return new BuildInfoRawData(getModel(), getBrand(), getManufacturer(), getAndroidVersion(), getSdkVersion(), getKernelVersion(), getFingerprint(), getHost(), getScreenResolution(), getScreenInches(), getScreenBrightness(), getScreenOffTimeout()).loadData();
    }
}
