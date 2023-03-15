package cn.tongdun.mobrisk.core.collectors;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import org.json.JSONArray;
import org.json.JSONObject;

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
        JSONArray sensorArray = new JSONArray();
        try {
            List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            for (int i = 0; i < sensorList.size(); i++) {
                Sensor sensor = sensorList.get(i);
                JSONObject sensorInfo = new JSONObject();
                sensorInfo.put("name", sensor.getName());
                sensorInfo.put("vendor", sensor.getVendor());
                sensorInfo.put("type", sensor.getType());
                sensorArray.put(i, sensorInfo);
            }
        } catch (Exception ignored) {
        }
        return sensorArray.toString();
    }
}
