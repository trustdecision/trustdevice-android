package cn.tongdun.mobrisk.providers.memory;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Memory info Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class MemoryInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;
    public MemoryInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getTotalMemory() {
        return mDeviceInfo.optString(Constants.KEY_TOTAL_MEMORY);
    }

    private String getAvailableMemory() {
        return mDeviceInfo.optString(Constants.KEY_AVAILABLE_MEMORY);
    }

    private String getTotalStorage() {
        return mDeviceInfo.optString(Constants.KEY_TOTAL_STORAGE);
    }

    private String getAvailableStorage() {
        return mDeviceInfo.optString(Constants.KEY_AVAILABLE_STORAGE);
    }

    @Override
    public String getProviderName() {
        return "Memory info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new MemoryInfoRawData(getTotalMemory(), getAvailableMemory(),getTotalStorage(),getAvailableStorage()).loadData();
    }
}
