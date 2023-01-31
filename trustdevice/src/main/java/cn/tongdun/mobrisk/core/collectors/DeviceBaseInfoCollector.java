package cn.tongdun.mobrisk.core.collectors;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * @description: DeviceBaseInfoCollector
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceBaseInfoCollector {

    private Context mContext;

    public DeviceBaseInfoCollector(Context context) {
        mContext = context;
    }

    public String getScreenResolution() {
        try {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            return outMetrics.widthPixels + "x" + outMetrics.heightPixels;
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getFilesAbsolutePath() {
        try {
            return mContext.getFilesDir().getAbsolutePath();
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getPackageName() {
        try {
            return mContext.getPackageName();
        } catch (Exception ignored) {
        }
        return "";
    }

    public boolean isHarmonyOS() {
        try {
            Class<?> clz = Class.forName("com.huawei.system.BuildEx");
            Method method = clz.getMethod("getOsBrand");
            String os = (String) method.invoke(clz);
            return "harmony".equalsIgnoreCase(os);
        } catch (Exception ignored) {
        }
        return false;
    }
}
