package cn.tongdun.mobrisk.core.utils;

/**
 * @description: constant
 * @author: wuzuchang
 * @date: 2022/12/7
 */
public class Constants {
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_DEVICE_RISK_LABEL = "device_risk_label";
    public static final String KEY_DEVICE_DETAIL = "device_detail";
    public static final String KEY_ANDROID_ID = "androidId";
    public static final String KEY_GSF_ID = "gsfId";
    public static final String KEY_MEDIA_DRM_ID = "mediaDrmId";
    public static final String KEY_VB_META_DIGEST = "vbMetaDigest";
    public static final String KEY_MODEL = "model";
    public static final String KEY_MANUFACTURER = "manufacturer";
    public static final String KEY_ANDROID_VERSION = "androidVersion";
    public static final String KEY_SDK_VERSION = "sdkVersion";
    public static final String KEY_KERNEL_VERSION = "kernelVersion";
    public static final String KEY_FINGERPRINT = "fingerprint";
    public static final String KEY_HARDWARE = "HARDWARE";
    public static final String KEY_DISPLAY = "display";
    public static final String KEY_HOST = "host";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_PRODUCT = "product";
    public static final String KEY_BATTERY_HEALTH_STATUS = "batteryHealthStatus";
    public static final String KEY_BATTERY_STATUS = "batteryStatus";
    public static final String KEY_BATTERY_LEVEL = "batteryLevel";
    public static final String KEY_BATTERY_TEMP = "batteryTemp";
    public static final String KEY_BATTERY_TOTAL_CAPACITY = "batteryTotalCapacity";
    public static final String KEY_CPU_PROCESSOR = "cpuProcessor";
    public static final String KEY_CPU_HARDWARE = "cpuHardware";
    public static final String KEY_CORES_COUNT = "coresCount";
    public static final String KEY_ABI_TYPE = "abiType";
    public static final String KEY_DEBUG = "debug";
    public static final String KEY_MULTIPLE = "multiple";
    public static final String KEY_ROOT = "root";
    public static final String KEY_XPOSED = "xposed";
    public static final String KEY_MAGISK = "magisk";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_TIMEZONE = "timezone";
    public static final String KEY_TOTAL_MEMORY = "totalMemory";
    public static final String KEY_AVAILABLE_MEMORY = "availableMemory";
    public static final String KEY_TOTAL_STORAGE = "totalStorage";
    public static final String KEY_AVAILABLE_STORAGE = "availableStorage";
    public static final String KEY_APP_LIST = "appList";
    public static final String KEY_SYSTEM_APP_LIST = "systemAppList";
    public static final String KEY_SENSORS_INFO = "sensorsInfo";
    public static final String KEY_ADB_ENABLED = "adbEnabled";
    public static final String KEY_DEVELOPMENT_SETTING_ENABLED = "developmentSettingEnabled";
    public static final String KEY_HTTP_PROXY = "httpProxy";
    public static final String KEY_DATA_ROAMING = "dataRoaming";
    public static final String KEY_ALLOW_MOCK_LOCATION = "allowMockLocation";
    public static final String KEY_ACCESSIBILITY_ENABLED = "accessibilityEnabled";
    public static final String KEY_DEFAULT_INPUTMETHOD = "defaultInputMethod";
    public static final String KEY_TOUCH_EXPLORATION_ENABLED = "touchExplorationEnabled";
    public static final String KEY_SCREEN_BRIGHTNESS = "screenBrightness";
    public static final String KEY_SCREEN_OFF_TIMEOUT = "screenOffTimeout";
    public static final String KEY_SCREEN_RESOLUTION = "screenResolution";
    public static final String KEY_SCREEN_INCHES = "screenInches";
    public static final String KEY_FILES_ABSOLUTE_PATH = "filesAbsolutePath";
    public static final String KEY_PACKAGE_NAME = "packageName";

    //magisk related files
    public static final String MAGISK_MOUNTS_PATH = "/proc/self/mounts";
    public static final String MAGISK_MOUNT_INFO_PATH = "/proc/self/mountinfo";
    public static final String MAGISK_MOUNT_STATS_PATH = "/proc/self/mountstats";

    public  static class JniCode{
        public static final int DEBUG = 0;
    }
}
