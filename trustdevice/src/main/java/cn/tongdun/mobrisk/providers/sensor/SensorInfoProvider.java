package cn.tongdun.mobrisk.providers.sensor;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.tongdun.mobrisk.core.utils.Constants;

/**
 * @description: Sensor info provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class SensorInfoProvider {
    private final JSONObject mDeviceInfo;

    public SensorInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    public int getSensorTotal() {
        if (mDeviceInfo == null) {
            return 0;
        }
        String sensorInfo = getSensorInfo();
        if (TextUtils.isEmpty(sensorInfo)) {
            return 0;
        }
        JSONArray sensorArray = null;
        try {
            sensorArray = new JSONArray(sensorInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sensorArray == null) {
            return 0;
        }
        return sensorArray.length();
    }

    public String getSensorInfo() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SENSORS_INFO);
    }

}
