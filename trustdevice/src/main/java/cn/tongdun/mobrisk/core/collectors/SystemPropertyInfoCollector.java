package cn.tongdun.mobrisk.core.collectors;

import android.annotation.SuppressLint;

/**
 * @description: SystemProperyInfoCollector
 * @author: wuzuchang
 * @date: 2022/12/8
 */
public class SystemPropertyInfoCollector {


    public SystemPropertyInfoCollector() {
    }

    public String getMetaDigest(){
       return getProperty("ro.boot.vbmeta.digest");
    }

    public String getProperty(String propertyName) {

        String value = "";
        try {
            @SuppressLint("PrivateApi")
            Class<?> systemPropertyClass = Class.forName("android.os.SystemProperties");
            value = (String) systemPropertyClass.getMethod("get", String.class).invoke(systemPropertyClass, propertyName);
        } catch (Exception ignored) {
        }
        return value;
    }
}
