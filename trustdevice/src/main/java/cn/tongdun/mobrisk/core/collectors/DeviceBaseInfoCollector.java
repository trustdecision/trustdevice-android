package cn.tongdun.mobrisk.core.collectors;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description: DeviceBaseInfoCollector
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceBaseInfoCollector {

    private Context mContext;
    private String mScreenResolution;
    private double mScreenInches;

    public DeviceBaseInfoCollector(Context context) {
        mContext = context;
        initScreenInfo();
    }


    private void initScreenInfo() {
        try {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            mScreenResolution = outMetrics.widthPixels + "x" + outMetrics.heightPixels;
            double x = Math.pow(outMetrics.widthPixels / outMetrics.xdpi, 2);
            double y = Math.pow(outMetrics.heightPixels / outMetrics.ydpi, 2);
            double inches = Math.sqrt(x + y);
            BigDecimal bigDecimal = new BigDecimal(inches);
            mScreenInches = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } catch (Exception ignored) {
        }
    }

    public String getScreenResolution() {

        return mScreenResolution;
    }

    public double getScreenInches() {
        return mScreenInches;
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
