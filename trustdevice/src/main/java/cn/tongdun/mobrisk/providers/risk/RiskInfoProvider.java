package cn.tongdun.mobrisk.providers.risk;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.providers.InfoProvider;

/**
 * @description: Risk info Provider
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class RiskInfoProvider {
    private final JSONObject mDeviceInfo;

    public RiskInfoProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_RISK_LABEL);
    }

    private boolean getRoot() {
        if (mDeviceInfo == null) {
            return false;
        }
        return mDeviceInfo.optBoolean(Constants.KEY_ROOT);
    }

    private boolean getDebug() {
        if (mDeviceInfo == null) {
            return false;
        }
        return mDeviceInfo.optBoolean(Constants.KEY_DEBUG);
    }

    private boolean getMultiple() {
        if (mDeviceInfo == null) {
            return false;
        }
        return mDeviceInfo.optBoolean(Constants.KEY_MULTIPLE);
    }

    private boolean getXposedStatus() {
        if (mDeviceInfo == null) {
            return false;
        }
        return mDeviceInfo.optBoolean(Constants.KEY_XPOSED);
    }

    private boolean getMagiskStatus() {
        if (mDeviceInfo == null) {
            return false;
        }
        return mDeviceInfo.optBoolean(Constants.KEY_MAGISK);
    }

    public String getRiskLabels() {
        List<String> riskLabels = new ArrayList<>();
        if (getRoot()) {
            riskLabels.add("root");
        }
        if (getDebug()) {
            riskLabels.add("debug");
        }
        if (getMultiple()) {
            riskLabels.add("multiple");
        }
        if (getXposedStatus()) {
            riskLabels.add("Xposed");
        }
        if (getMagiskStatus()) {
            riskLabels.add("Magisk");
        }
        return TextUtils.join(",", riskLabels);
    }
}
