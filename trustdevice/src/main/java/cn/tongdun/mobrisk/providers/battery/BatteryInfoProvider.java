package cn.tongdun.mobrisk.providers.battery;

import org.json.JSONObject;

import cn.tongdun.mobrisk.core.utils.Constants;

/**
 * @description: BatteryInfo Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BatteryInfoProvider {

    private final JSONObject mDeviceInfo;

    public BatteryInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    public String getHealth() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_HEALTH_STATUS);
    }

    public String getStatus() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_STATUS);
    }

    public int getLevel() {
        if (mDeviceInfo == null) {
            return 0;
        }
        return mDeviceInfo.optInt(Constants.KEY_BATTERY_LEVEL);
    }

    public int getTemp() {
        if (mDeviceInfo == null) {
            return 0;
        }
        return mDeviceInfo.optInt(Constants.KEY_BATTERY_TEMP);
    }

    public String getTotalCapacity() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_BATTERY_TOTAL_CAPACITY);
    }
}
