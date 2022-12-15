package cn.tongdun.mobrisk.providers.device_id;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: DeviceId Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class DeviceIdProvider extends InfoProvider<String> {

    private JSONObject mDeviceInfo;

    public DeviceIdProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo;
    }

    private String getDeviceId() {

        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DEVICE_ID);
    }

    private String getAndroidId() {
        if (mDeviceInfo == null) {
            return "";
        }
        JSONObject deviceDetail = mDeviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
        if (deviceDetail == null) {
            return "";
        }
        return getData(Constants.KEY_ANDROID_ID);
    }

    private String getGsfId() {
        if (mDeviceInfo == null) {
            return "";
        }
        JSONObject deviceDetail = mDeviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
        if (deviceDetail == null) {
            return "";
        }
        return getData(Constants.KEY_GSF_ID);
    }

    private String getMediaDrmId() {
        return getData(Constants.KEY_MEDIA_DRM_ID);
    }

    private String getVbMetaDigest() {
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

    @Override
    public String getProviderName() {
        return "Device id";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new DeviceIdRawData(getDeviceId(), getAndroidId(), getGsfId(), getMediaDrmId(), getVbMetaDigest()).loadData();
    }
}
