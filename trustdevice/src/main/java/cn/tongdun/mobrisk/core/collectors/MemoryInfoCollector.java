package cn.tongdun.mobrisk.core.collectors;

import android.app.ActivityManager;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @description: Memory Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class MemoryInfoCollector {

    private long mAvailableMemory;
    private long mTotalMemory;
    private long mAvailableStorage;
    private long mTotalStorage;

    public MemoryInfoCollector(ActivityManager activityManager) {
        getMemoryInfo(activityManager);
        getStorageInfo();
    }

    private void getMemoryInfo(ActivityManager activityManager) {
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            mAvailableMemory = memoryInfo.availMem;
            mTotalMemory = memoryInfo.totalMem;
        } catch (Exception ignored) {
        }
    }

    public void getStorageInfo() {
        try {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            long totalBlocks = stat.getBlockCountLong();
            mTotalStorage = totalBlocks * blockSize;
            mAvailableStorage = availableBlocks * blockSize;
        } catch (Exception ignored) {
        }
    }

    public long getTotalMemory() {
        return mTotalMemory;
    }

    public long getAvailableMemory() {
        return mAvailableMemory;
    }

    public long getAvailableStorage() {
        return mAvailableStorage;
    }

    public long getTotalStorage() {
        return mTotalStorage;
    }
}
