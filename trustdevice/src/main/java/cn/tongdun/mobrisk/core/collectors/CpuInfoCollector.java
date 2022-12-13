package cn.tongdun.mobrisk.core.collectors;

import android.os.Build;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tongdun.mobrisk.core.utils.FileUtils;

/**
 * @description: Cpu info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class CpuInfoCollector {
    private static final List<String> keyList = Arrays.asList("Processor", "Hardware");
    private static final String CPU_INFO_PATH = "/proc/cpuinfo";
    private static final String KEY_VALUE_DELIMITER = ": ";
    private Map<String, String> mCpuInfo = new HashMap<>();

    public CpuInfoCollector() {
        try {
            mCpuInfo = FileUtils.readFileByKey(CPU_INFO_PATH, keyList, KEY_VALUE_DELIMITER);
        } catch (Exception ignored) {
        }
    }

    public String getCpuProcessor() {
        try {
            return mCpuInfo.get(keyList.get(0));
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getCpuHardware() {
        try {
            return mCpuInfo.get(keyList.get(1));
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getAbiType() {
        try {
            return TextUtils.join(",", Build.SUPPORTED_ABIS);
        } catch (Exception ignored) {
        }
        return "";
    }

    public String availableProcessors() {
        try {
            return String.valueOf(Runtime.getRuntime().availableProcessors());
        } catch (Exception ignored) {
        }
        return "";
    }
}
