package cn.tongdun.mobrisk;

import org.json.JSONObject;

public interface TDRiskCallback {
    void onEvent(JSONObject deviceInfo);
}
