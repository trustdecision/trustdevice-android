package cn.tongdun.mobrisk.core.collectors;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.File;

/**
 * @description: detect Emulator
 * @author: wuzuchang
 * @date: 2023/4/25
 */
public class EmulatorCollector {

    private static final String[] QEMU_FILES = {
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
            "/system/bin/microvirtd"
    };

    public boolean detectEmulator() {
        boolean isEmulator = checkBuildInfo();
        if (!isEmulator){
            isEmulator = checkFiles();
        }
        return isEmulator;
    }

    @SuppressLint("HardwareIds")
    private boolean checkBuildInfo() {
        return "nox".equals(Build.DEVICE)
                || "vbox86p".equals(Build.DEVICE)
                || "vbox86tp".equals(Build.DEVICE)
                || "appplayer".equals(Build.DEVICE)
                || "droid4x".equals(Build.DEVICE)
                || "vbox".equals(Build.DEVICE)
                || "virtual".equals(Build.DEVICE)
                || "andywin".equals(Build.DEVICE)
                || "andyosx".equals(Build.DEVICE)
                || "generic".equals(Build.DEVICE)
                || "generic_x86".equals(Build.DEVICE)
                || Build.DEVICE.toLowerCase().contains("mumu")
                || Build.DEVICE.toLowerCase().contains("zerofltezc")
                || Build.FINGERPRINT.startsWith("generic")
                || "vmos".equals(Build.MODEL)
                || "duos".equals(Build.MODEL)
                || "amiduos".equals(Build.MODEL)
                || "noxw".equals(Build.MODEL)
                || "genymotion".equals(Build.MODEL)
                || "bluestacks".equals(Build.MODEL)
                || "tiantian".equals(Build.MODEL)
                || "windroy".equals(Build.MODEL)
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.toLowerCase().contains("droid4x")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.contains("Subsystem for Android(TM)")
                || Build.MANUFACTURER.contains("Genymotion")
                || "goldfish".equals(Build.HARDWARE)
                || "vbox86".equals(Build.HARDWARE)
                || "sdk".equals(Build.PRODUCT)
                || "google_sdk".equals(Build.PRODUCT)
                || "sdk_x86".equals(Build.PRODUCT)
                || "vbox86p".equals(Build.PRODUCT)
                || "vbox86tp".equals(Build.PRODUCT)
                || "genymotion".equals(Build.PRODUCT)
                || "bluestacks".equals(Build.PRODUCT)
                || "droid4x".equals(Build.PRODUCT)
                || "ttvm_hdragon".equals(Build.PRODUCT)
                || "duos_native".equals(Build.PRODUCT)
                || "duos".equals(Build.PRODUCT)
                || "vbox".equals(Build.PRODUCT)
                || "android_x86".equals(Build.PRODUCT)
                || Build.PRODUCT.toLowerCase().contains("nox")
                || Build.BOARD.toLowerCase().contains("nox")
                || Build.BOOTLOADER.toLowerCase().contains("nox")
                || Build.HARDWARE.toLowerCase().contains("nox")
                || Build.SERIAL.toLowerCase().contains("nox")
                || Build.HOST.toLowerCase().startsWith("bliss-os")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }

    private boolean checkFiles() {
        for (String filePtah : QEMU_FILES) {
            File qemu_file = new File(filePtah);
            if (qemu_file.exists()) {
                return true;
            }
        }
        return false;
    }

}
