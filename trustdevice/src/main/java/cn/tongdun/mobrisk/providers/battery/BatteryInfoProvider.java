package cn.tongdun.mobrisk.providers.battery;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: BatteryInfo Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BatteryInfoProvider extends InfoProvider<String> {

    private final JSONObject mDeviceInfo;

    public BatteryInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    @Override
    public String getProviderName() {
        return "Battery info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new BatteryInfoRawData(getHealth(), getStatus(), getLevel(), getTemp(), getTotalCapacity()).loadData();
    }

    private String getHealth() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_HEALTH_STATUS);
    }

    private String getStatus() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_STATUS);
    }

    private String getLevel() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_LEVEL);
    }

    private String getTemp() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_TEMP);
    }

    private String getTotalCapacity() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_TOTAL_CAPACITY);
    }
}
