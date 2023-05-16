package cn.tongdun.mobrisk.core.collectors

import android.os.Build
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description: Build Info
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface BuildInfoInterface {
    fun getModel(): String
    fun getManufacturer(): String
    fun getAndroidVersion(): String
    fun getSdkVersion(): String
    fun getKernelVersion(): String
    fun getFingerprint(): String
    fun getHardware(): String
    fun getBrand(): String
    fun getHost(): String
    fun getDisplay(): String
    fun getProduct(): String
}

class BuildInfoCollector : BuildInfoInterface {
    override fun getModel(): String = executeSafe({ Build.MODEL }, "")

    override fun getManufacturer(): String = executeSafe({ Build.MANUFACTURER }, "")

    override fun getAndroidVersion(): String = executeSafe({ Build.VERSION.RELEASE }, "")

    override fun getSdkVersion(): String = executeSafe({ Build.VERSION.SDK_INT.toString() }, "")

    override fun getKernelVersion(): String =
        executeSafe({ System.getProperty("os.version") ?: "" }, "")

    override fun getFingerprint(): String = executeSafe({ Build.FINGERPRINT }, "")

    override fun getHardware(): String = executeSafe({ Build.HARDWARE }, "")

    override fun getBrand(): String = executeSafe({ Build.BRAND }, "")

    override fun getHost(): String = executeSafe({ Build.HOST }, "")

    override fun getDisplay(): String = executeSafe({ Build.DISPLAY }, "")

    override fun getProduct(): String = executeSafe({ Build.PRODUCT }, "")
}