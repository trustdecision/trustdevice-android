package cn.tongdun.mobrisk.providers.applist;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: RiskInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class AppListRawData extends RawData<String[]> {
    private String appList;
    private String systemAppList;

    public AppListRawData(String appList, String systemAppList) {
        this.appList = appList;
        this.systemAppList = systemAppList;
    }

    private String[] getAppList() {
        return appList.split(",");
    }

    private String[] getSystemAppList() {
        return systemAppList.split(",");
    }

    @Override
    public List<Pair<String, String[]>> loadData() {
        List<Pair<String, String[]>> data = new ArrayList<>();
        data.add(new Pair<>("App list", getAppList()));
        data.add(new Pair<>("System app list", getSystemAppList()));
        return data;
    }
}
