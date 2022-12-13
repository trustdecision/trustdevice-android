package cn.tongdun.mobrisk.core.collectors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Sensors Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class SensorsInfoCollector {

    private SensorManager mSensorManager;

    public SensorsInfoCollector(SensorManager sensorManager) {
        mSensorManager = sensorManager;
    }

    public String getSensorList() {
        try {
            List<String> sensorInfos = new ArrayList<>();
            List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            for (Sensor sensor : sensorList) {
                sensorInfos.add(sensor.getName() + ":" + sensor.getVendor());
            }
            return TextUtils.join(",", sensorInfos);
        } catch (Exception ignored) {
        }
        return "";
    }
}
