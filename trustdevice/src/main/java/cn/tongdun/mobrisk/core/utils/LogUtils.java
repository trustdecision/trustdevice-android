package cn.tongdun.mobrisk.core.utils;

import android.util.Log;

/**
 * @description: log util
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class LogUtils {

    private static final String LOG_TAG = "TrustDevice";

    public static void d(String message) {
        Log.d(LOG_TAG, message);
    }

    public static void e(String message) {
        Log.e(LOG_TAG, message);
    }
}
