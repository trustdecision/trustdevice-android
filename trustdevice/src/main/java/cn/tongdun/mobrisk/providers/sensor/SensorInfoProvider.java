package cn.tongdun.mobrisk.providers.sensor;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Sensor info provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class SensorInfoProvider{
    private final JSONObject mDeviceInfo;

    public SensorInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    public int getSensorTotal() {
        if (mDeviceInfo == null) {
            return 0;
        }
        return mDeviceInfo.optString(Constants.KEY_SENSORS_INFO).split(",").length;
    }

    public String getSensorInfo() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SENSORS_INFO);
    }

}
