package cn.tongdun.mobrisk.providers.risk;

import android.util.Pair;

import org.json.JSONObject;

import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Risk info Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class RiskInfoProvider extends InfoProvider<String> {
    private final JSONObject mDeviceInfo;

    public RiskInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_RISK_LABEL);
    }

    private String getRoot() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_ROOT);
    }

    private String getDebug() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_DEBUG);
    }

    private String getMultiple() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_MULTIPLE);
    }

    @Override
    public String getProviderName() {
        return "Risk info";
    }

    @Override
    public List<Pair<String, String>> getRawData() {
        return new RiskInfoRawData(getRoot(), getDebug(), getMultiple()).loadData();
    }
}
