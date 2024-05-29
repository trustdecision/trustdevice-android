package cn.tongdun.mobrisk.core.collectors

import android.os.Build
import cn.tongdun.mobrisk.core.collectors.EmulatorCollector
import java.io.File
import java.util.*

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface EmulatorInterface {
    fun detectEmulator(): Boolean
}

class EmulatorCollector : EmulatorInterface {

    private val QEMU_FILES = listOf(
        "/dev/socket/genyd",
        "/dev/socket/baseband_genyd",
        "/dev/socket/qemud",
        "/dev/qemu_pipe",
        "/system/bin/nox-prop",
        "/system/bin/ttVM-prop",
        "/system/bin/droid4x-prop",
        "/data/.bluestacks.prop",
        "/system/bin/androVM-prop",
        "/system/bin/microvirt-prop",
        "/system/lib/libdroid4x.so",
        "/system/bin/windroyed",
        "system/lib/libnoxspeedup.so",
        "/system/bin/duosconfig",
        "/system/etc/xxzs_prop.sh",
        "/system/etc/mumu-configs/device-prop-configs/mumu.config",
        "/system/priv-app/ldAppStore",
        "system/bin/ldinit",
        "/system/app/AntStore",
        "vmos.prop",
        "fstab.titan",
        "x8.prop",
        "/system/lib/libc_malloc_debug_qemu.so"
    )

    private fun checkDevice(): Boolean = "nox" == Build.DEVICE
            || "vbox86p" == Build.DEVICE
            || "vbox86tp" == Build.DEVICE
            || "appplayer" == Build.DEVICE
            || "droid4x" == Build.DEVICE
            || "vbox" == Build.DEVICE
            || "virtual" == Build.DEVICE
            || "andywin" == Build.DEVICE
            || "andyosx" == Build.DEVICE
            || "generic" == Build.DEVICE
            || "generic_x86" == Build.DEVICE
            || "emu64a" == Build.DEVICE
            || Build.DEVICE.lowercase(Locale.getDefault()).contains("mumu")
            || Build.DEVICE.lowercase(Locale.getDefault()).contains("zerofltezc")

    private fun checkModel(): Boolean = "vmos" == Build.MODEL
            || "duos" == Build.MODEL
            || "amiduos" == Build.MODEL
            || "noxw" == Build.MODEL
            || "genymotion" == Build.MODEL
            || "bluestacks" == Build.MODEL
            || "tiantian" == Build.MODEL
            || "windroy" == Build.MODEL
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.lowercase(Locale.getDefault()).contains("droid4x")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MODEL.contains("Subsystem for Android(TM)")

    private fun checkFingerprint(): Boolean = Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.contains("vbox")

    private fun checkProduct(): Boolean = "sdk" == Build.PRODUCT
            || "google_sdk" == Build.PRODUCT
            || "sdk_x86" == Build.PRODUCT
            || "vbox86p" == Build.PRODUCT
            || "vbox86tp" == Build.PRODUCT
            || "genymotion" == Build.PRODUCT
            || "bluestacks" == Build.PRODUCT
            || "droid4x" == Build.PRODUCT
            || "ttvm_hdragon" == Build.PRODUCT
            || "duos_native" == Build.PRODUCT
            || "duos" == Build.PRODUCT
            || "vbox" == Build.PRODUCT
            || "android_x86" == Build.PRODUCT
            || Build.PRODUCT.lowercase(Locale.getDefault()).contains("nox")

    private fun checkHardware(): Boolean = "goldfish" == Build.HARDWARE
            || "vbox86" == Build.HARDWARE

    private fun checkOtherBuildInfo(): Boolean = Build.MANUFACTURER.contains("Genymotion")
            || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
            || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
            || Build.HARDWARE.lowercase(Locale.getDefault()).contains("nox")
            || Build.SERIAL.lowercase(Locale.getDefault()).contains("nox")
            || Build.HOST.lowercase(Locale.getDefault()).startsWith("bliss-os")
            || Build.HOST.lowercase(Locale.getDefault()).contentEquals("G1-SNIPER-B7")
            || Build.HOST.lowercase(Locale.getDefault()).contentEquals("Build2")
            || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))

    override fun detectEmulator(): Boolean {
        var isEmulator = checkBuildInfo()
        if (!isEmulator) {
            isEmulator = checkFiles()
        }
        return isEmulator
    }

    private fun checkBuildInfo(): Boolean =
        checkDevice() ||
                checkModel() ||
                checkFingerprint() ||
                checkProduct() ||
                checkHardware() ||
                checkOtherBuildInfo()

    private fun checkFiles(): Boolean {
        for (filePtah in QEMU_FILES) {
            if (File(filePtah).exists()) {
                return true
            }
        }
        return false
    }
}