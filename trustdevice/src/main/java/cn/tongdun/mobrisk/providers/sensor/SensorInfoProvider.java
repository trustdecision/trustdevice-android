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
public class SensorInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;

    public SensorInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getSensorInfo() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SENSORS_INFO);
    }

    @Override
    public String getProviderName() {
        return "Sensor info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new SensorInfoRawData(getSensorInfo()).loadData();
    }
}
