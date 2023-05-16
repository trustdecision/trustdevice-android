package cn.tongdun.mobrisk.core.collectors

import android.app.ActivityManager
import android.os.Environment
import android.os.StatFs
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description:MemoryInfo
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface MemoryInfoInterface {
    fun getTotalMemory(): Long
    fun getAvailableMemory(): Long
    fun getAvailableStorage(): Long
    fun getTotalStorage(): Long
}

class MemoryInfoCollector(private val activityManager: ActivityManager) : MemoryInfoInterface {
    private var mAvailableMemory = 0L
    private var mTotalMemory = 0L
    private var mAvailableStorage = 0L
    private var mTotalStorage = 0L

    init {
        executeSafe {
            val memoryInfo = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memoryInfo)
            mAvailableMemory = memoryInfo.availMem
            mTotalMemory = memoryInfo.totalMem

            val path = Environment.getDataDirectory()
            val stat = StatFs(path.path)
            val blockSize = stat.blockSizeLong
            val availableBlocks = stat.availableBlocksLong
            val totalBlocks = stat.blockCountLong
            mTotalStorage = totalBlocks * blockSize
            mAvailableStorage = availableBlocks * blockSize
        }
    }

    override fun getTotalMemory(): Long {
        return mTotalMemory
    }

    override fun getAvailableMemory(): Long {
        return mAvailableMemory
    }

    override fun getAvailableStorage(): Long {
        return mAvailableStorage
    }

    override fun getTotalStorage(): Long {
        return mTotalStorage
    }

}