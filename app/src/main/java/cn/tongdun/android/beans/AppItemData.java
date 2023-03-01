package cn.tongdun.android.beans;


import android.graphics.drawable.Drawable;
import android.os.Build;

import java.util.Objects;

/**
 * @description:  app item
 * @author: wuzuchang
 * @date: 2023/3/8
 */
public class AppItemData {

    private Drawable icon;
    private String appName;
    private String packageName;
    private String versionName;


    public AppItemData(Drawable icon, String appName, String packageName, String versionName) {
        this.icon = icon;
        this.appName = appName;
        this.packageName = packageName;
        this.versionName = versionName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppItemData)) return false;
        AppItemData that = (AppItemData) o;
        return getAppName().equals(that.getAppName()) && getPackageName().equals(that.getPackageName()) && getVersionName().equals(that.getVersionName());
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(getAppName(), getPackageName(), getVersionName());
        }
        return 0;
    }
}
