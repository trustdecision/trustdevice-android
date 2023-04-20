package cn.tongdun.mobrisk.providers.risk;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.core.utils.LogUtils;
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
        int debug = mDeviceInfo.optInt(Constants.KEY_DEBUG);
        return debug > 0;
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

    private boolean getHookStatus() {
        if (mDeviceInfo == null) {
            return false;
        }
        LogUtils.d("mDeviceInfo.optString(Constants.KEY_HOOK)=" + mDeviceInfo.optString(Constants.KEY_HOOK));
        return !TextUtils.isEmpty(mDeviceInfo.optString(Constants.KEY_HOOK));
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
        if (getHookStatus()) {
            riskLabels.add("Hook");
        }
        return TextUtils.join(", ", riskLabels);
    }
}
