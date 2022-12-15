package cn.tongdun.mobrisk.providers.cpu;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: CpuInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class CpuInfoRawData extends RawData<String> {
    private String cpuHardware;
    private String cpuProcessor;
    private String abiType;
    private String coresCount;

    public CpuInfoRawData(String cpuHardware, String cpuProcessor, String abiType, String coresCount) {
        this.cpuHardware = cpuHardware;
        this.cpuProcessor = cpuProcessor;
        this.abiType = abiType;
        this.coresCount = coresCount;
    }

    private String getCpuHardware() {
        return cpuHardware;
    }

    private String getCpuProcessor() {
        return cpuProcessor;
    }

    private String getAbiType() {
        return abiType;
    }

    private String getCoresCount() {
        return coresCount;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("hardware", getCpuHardware()));
        data.add(new Pair<>("processor", getCpuProcessor()));
        data.add(new Pair<>("abi type", getAbiType()));
        data.add(new Pair<>("cores count", getCoresCount()));
        return data;
    }
}
