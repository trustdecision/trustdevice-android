package cn.tongdun.mobrisk.providers.memory;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: MemoryInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class MemoryInfoRawData extends RawData<String> {
    String totalMemory;
    String availableMemory;
    String totalStorage;
    String availableStorage;

    public MemoryInfoRawData(String totalMemory, String availableMemory, String totalStorage, String availableStorage) {
        this.totalMemory = totalMemory;
        this.availableMemory = availableMemory;
        this.totalStorage = totalStorage;
        this.availableStorage = availableStorage;
    }

    private String getTotalMemory() {
        return String.format(Locale.US, "%.2fMB", Long.parseLong(totalMemory) / Math.pow(1024, 2));
    }

    private String getAvailableMemory() {
        return String.format(Locale.US, "%.2fMB", Long.parseLong(availableMemory) / Math.pow(1024, 2));
    }

    private String getTotalStorage() {
        return String.format(Locale.US, "%.2fGB", Long.parseLong(totalStorage) / Math.pow(1024, 3));
    }

    private String getAvailableStorage() {
        return String.format(Locale.US, "%.2fGB", Long.parseLong(availableStorage) / Math.pow(1024, 3));
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("total memory", getTotalMemory()));
        data.add(new Pair<>("available memory", getAvailableMemory()));
        data.add(new Pair<>("total storage", getTotalStorage()));
        data.add(new Pair<>("available storage", getAvailableStorage()));
        return data;
    }
}
