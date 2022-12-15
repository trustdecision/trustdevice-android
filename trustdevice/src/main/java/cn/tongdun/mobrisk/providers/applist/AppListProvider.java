package cn.tongdun.mobrisk.providers.applist;

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
public class AppListProvider extends InfoProvider<String[]> {
    private final JSONObject mDeviceInfo;

    public AppListProvider(JSONObject deviceInfo) {
        mDeviceInfo = deviceInfo.optJSONObject(Constants.KEY_DEVICE_DETAIL);
    }

    private String getAppList() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_APP_LIST);
    }

    private String getSystemAppList() {
        if (mDeviceInfo == null) {
            return "";
        }
        return mDeviceInfo.optString(Constants.KEY_SYSTEM_APP_LIST);
    }

    @Override
    public String getProviderName() {
        return "App list";
    }

    @Override
    public List<Pair<String, String[]>> getRawData() {
        return new AppListRawData(getAppList(), getSystemAppList()).loadData();
    }
}
