package cn.tongdun.mobrisk.core.collectors

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import cn.tongdun.mobrisk.core.tools.executeSafe
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * @description:Device Base Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface DeviceBaseInfoInterface {
    fun getScreenResolution(): String
    fun getScreenInches(): Double
    fun getFilesAbsolutePath(): String
    fun getPackageName(): String
    fun isHarmonyOS(): Boolean
}

class DeviceBaseInfoCollector(private val context: Context) : DeviceBaseInfoInterface {

    private lateinit var mScreenResolution: String
    private var mScreenInches = 0.0

    init {
        executeSafe {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(outMetrics)
            mScreenResolution = "${outMetrics.widthPixels}x${outMetrics.heightPixels}"
            val x = (outMetrics.widthPixels / outMetrics.xdpi).toDouble().pow(2.0);
            val y = (outMetrics.heightPixels / outMetrics.ydpi).toDouble().pow(2.0);
            val inches = sqrt(x + y);
            val bigDecimal = BigDecimal(inches);
            mScreenInches = bigDecimal.setScale(2, RoundingMode.HALF_UP).toDouble();
        }
    }

    override fun getScreenResolution(): String = mScreenResolution

    override fun getScreenInches(): Double = mScreenInches

    override fun getFilesAbsolutePath(): String = executeSafe({ context.filesDir.absolutePath }, "")

    override fun getPackageName(): String = executeSafe({ context.packageName }, "")

    override fun isHarmonyOS(): Boolean = executeSafe({
        val clz = Class.forName("com.huawei.system.BuildEx")
        val method = clz.getMethod("getOsBrand")
        val os = method.invoke(clz) as String
        "harmony".equals(os, ignoreCase = true)
    }, false)
}