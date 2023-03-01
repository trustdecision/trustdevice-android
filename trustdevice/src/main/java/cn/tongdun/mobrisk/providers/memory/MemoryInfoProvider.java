package cn.tongdun.mobrisk.providers.memory;

import org.json.JSONObject;

import cn.tongdun.mobrisk.core.utils.Constants;

/**
 * @description: Memory info Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class MemoryInfoProvider {
    private final JSONObject mDeviceInfo;

    public MemoryInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    public long getTotalMemory() {
        return mDeviceInfo.optLong(Constants.KEY_TOTAL_MEMORY);
    }

    public long getAvailableMemory() {
        return mDeviceInfo.optLong(Constants.KEY_AVAILABLE_MEMORY);
    }

    public long getTotalStorage() {
        return mDeviceInfo.optLong(Constants.KEY_TOTAL_STORAGE);
    }

    public long getAvailableStorage() {
        return mDeviceInfo.optLong(Constants.KEY_AVAILABLE_STORAGE);
    }
}
