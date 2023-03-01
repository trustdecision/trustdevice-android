package cn.tongdun.mobrisk.providers.device_id;

import org.json.JSONObject;

import cn.tongdun.mobrisk.core.utils.Constants;

/**
 * @description: DeviceId Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class DeviceIdProvider {

    private JSONObject mDeviceInfo;

    public DeviceIdProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo;
    }

    public String getDeviceId() {

        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DEVICE_ID);
    }

    public String getAndroidId() {
        if (mDeviceInfo == null) {
            return "";
        }
        JSONObject deviceDetail = mDeviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
        if (deviceDetail == null) {
            return "";
        }
        return getData(Constants.KEY_ANDROID_ID);
    }

    public String getGsfId() {
        if (mDeviceInfo == null) {
            return "";
        }
        JSONObject deviceDetail = mDeviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
        if (deviceDetail == null) {
            return "";
        }
        return getData(Constants.KEY_GSF_ID);
    }

    public String getMediaDrmId() {
        return getData(Constants.KEY_MEDIA_DRM_ID);
    }

    public String getVbMetaDigest() {
        return getData(Constants.KEY_VB_META_DIGEST);
    }


    private String getData(String Key) {
        if (mDeviceInfo == null) {
            return "";
        }
        JSONObject deviceDetail = mDeviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
        if (deviceDetail == null) {
            return "";
        }
        return deviceDetail.optString(Key);
    }
}
