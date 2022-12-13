package cn.tongdun.mobrisk.providers.cpu;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: CpuInfo Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class CpuInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;

    public CpuInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getCpuHardware() {
        return mDeviceInfo.optString(Constants.KEY_CPU_HARDWARE);
    }

    private String getCpuProcessor() {
        return mDeviceInfo.optString(Constants.KEY_CPU_PROCESSOR);
    }

    private String getAbiType() {
        return mDeviceInfo.optString(Constants.KEY_ABI_TYPE);
    }

    private String getCoresCount() {
        return mDeviceInfo.optString(Constants.KEY_CORES_COUNT);
    }

    @Override
    public String getProviderName() {
        return "Cpu info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new CpuInfoRawData(getCpuHardware(), getCpuProcessor(), getAbiType(), getCoresCount()).loadData();
    }
}
