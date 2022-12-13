package cn.tongdun.mobrisk.providers.build;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: BuildInfo Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BuildInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;

    public BuildInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getModel() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_MODEL);
    }

    private String getBrand() {
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

    public String getSdkVersion() {
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

    @Override
    public String getProviderName() {
        return "Build info ";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new BuildInfoRawData(getModel(), getBrand(), getManufacturer(), getAndroidVersion(), getSdkVersion(), getKernelVersion(), getFingerprint(), getHost()).loadData();
    }
}
