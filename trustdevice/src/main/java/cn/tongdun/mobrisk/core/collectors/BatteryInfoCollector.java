package cn.tongdun.mobrisk.core.collectors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import java.lang.reflect.Method;

/**
 * @description: Battery Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class BatteryInfoCollector {

    private static final String POWER_PROFILE_CLASS_NAME = "com.android.internal.os.PowerProfile";
    private static final String BATTERY_CAPACITY_METHOD_NAME = "getBatteryCapacity";

    private final Context mContext;
    private String batteryHealthStatus;
    private String batteryStatus;
    private int batteryLevel;
    private int batteryTemp;

    public BatteryInfoCollector(Context context) {
        mContext = context;
        getBatteryInfo();
    }

    private void getBatteryInfo() {
        Intent intent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED), "com.permission.broadcast.td", null);
        if (intent==null){
            return;
        }
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        batteryHealthStatus = batteryHealthStatus(health);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
        batteryStatus = batteryStatus(status);
        batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        batteryTemp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
    }

    private String batteryHealthStatus(int batteryHealth) {
        String type;
        switch (batteryHealth) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                type = "good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                type = "overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                type = "cold";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                type = "dead";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                type = "over voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                type = "unspecified failure";
                break;
            default:
                type = "unknown";
                break;
        }
        return type;
    }

    private String batteryStatus(int status) {
        String statusStr;
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusStr = "charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusStr = "discharging";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusStr = "not charging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusStr = "full";
                break;
            default:
                statusStr = "unknown";
                break;
        }
        return statusStr;
    }

    public String getBatteryHealthStatus() {
        return batteryHealthStatus;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getBatteryTemp() {
        return batteryTemp;
    }

    @SuppressLint("PrivateApi")
    public String getBatteryTotalCapacity() {
        try {
            Class<?> powerProfileClass = Class.forName(POWER_PROFILE_CLASS_NAME);
            Object object = powerProfileClass.getConstructor(Context.class).newInstance(mContext);
            Method getBatteryCapacity = powerProfileClass.getMethod(BATTERY_CAPACITY_METHOD_NAME);
            double totalCapacity = (double) getBatteryCapacity.invoke(object);
            return String.valueOf(totalCapacity);
        } catch (Throwable ignored) {
        }
        return "";
    }
}
