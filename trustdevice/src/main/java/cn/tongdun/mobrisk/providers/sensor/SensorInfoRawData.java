package cn.tongdun.mobrisk.providers.sensor;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: SensorInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class SensorInfoRawData extends RawData<String> {
    private String sensorInfo;

    public SensorInfoRawData(String sensorInfo) {
        this.sensorInfo = sensorInfo;
    }

    private String[] getSensorInfo() {
        return sensorInfo.split(",");
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        for (String sensorInfo : getSensorInfo()) {
            String[] sensor = sensorInfo.split(":");
            if (sensor.length >= 2) {
                data.add(new Pair<>(sensor[0], sensor[1]));
            }
        }
        return data;
    }
}
