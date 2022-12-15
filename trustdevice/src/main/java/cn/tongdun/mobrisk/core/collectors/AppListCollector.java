package cn.tongdun.mobrisk.core.collectors;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: PackageListInfo
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class AppListCollector {
    private List<String> appList = new ArrayList<>();
    private List<String> systemAppList = new ArrayList<>();

    @SuppressLint("QueryPermissionsNeeded")
    public AppListCollector(PackageManager packageManager) {
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfoList) {
            if (isSystemApp(packageInfo)) {
                systemAppList.add(packageInfo.packageName);
            } else {
                appList.add(packageInfo.packageName);
            }
        }
    }

    public String getAppList() {
        return TextUtils.join(",", appList);
    }

    public String getSystemAppList() {
        return TextUtils.join(",", systemAppList);
    }

    private boolean isSystemApp(PackageInfo packageInfo) {
        boolean isSysApp = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM;
        boolean isSysUpd = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return isSysApp || isSysUpd;
    }
}
