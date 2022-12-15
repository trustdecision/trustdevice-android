package cn.tongdun.mobrisk.core.collectors;

import android.os.Build;

/**
 * @description: BuildInfo
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class BuildInfoCollector {

    public String getModel() {
        try {
            return Build.MODEL;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getManufacturer() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getAndroidVersion() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getSdkVersion() {
        try {
            return String.valueOf(Build.VERSION.SDK_INT);
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getKernelVersion() {
        try {
            return System.getProperty("os.version");
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getFingerprint() {
        try {
            return Build.FINGERPRINT;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getHardware() {
        try {
            return Build.HARDWARE;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getBrand() {
        try {
            return Build.BRAND;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getHost() {
        try {
            return Build.HOST;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getDisplay() {
        try {
            return Build.DISPLAY;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getProduct() {
        try {
            return Build.PRODUCT;
        } catch (Exception ignored) {
        }
        return "";
    }

}
