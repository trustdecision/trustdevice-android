package cn.tongdun.mobrisk.providers.battery;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: BatteryInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BatteryInfoRawData extends RawData<String> {
    private String health;
    private String status;
    private String level;
    private String temp;
    private String totalCapacity;

    public BatteryInfoRawData(String health, String status, String level, String temp, String totalCapacity) {
        this.health = health;
        this.status = status;
        this.level = level;
        this.temp = temp;
        this.totalCapacity = totalCapacity;
    }

    private String getHealth() {
        return health;
    }

    private String getStatus() {
        return status;
    }

    private String getLevel() {
        return level;
    }

    private String getTemp() {
        return temp;
    }

    private String getTotalCapacity() {
        return totalCapacity;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("health", getHealth()));
        data.add(new Pair<>("status", getStatus()));
        data.add(new Pair<>("level", getLevel()));
        data.add(new Pair<>("temp", getTemp()));
        data.add(new Pair<>("total capacity", getTotalCapacity()));
        return data;
    }
}
