package cn.tongdun.mobrisk.beans;

/**
 * @description: DeviceInfo
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceInfo {

    private String androidId;
    private String gsfId;
    private String mediaDrmId;
    private String vbMetaDigest;
    private String model;
    private String manufacturer;
    private String androidVersion;
    private String sdkVersion;
    private String kernelVersion;
    private String fingerprint;
    private String hardware;
    private String product;
    private String brand;
    private String display;
    private String host;
    private String batteryHealthStatus;
    private String batteryStatus;
    private String batteryLevel;
    private String batteryTemp;
    private String batteryTotalCapacity;
    private String cpuProcessor;
    private String cpuHardware;
    private String coresCount;
    private String abiType;
    private int debug;
    private boolean root;
    private boolean xposed;
    private boolean magisk;
    private String hook;
    private String country;
    private String language;
    private String timezone;
    private String totalMemory;
    private String availableMemory;
    private String totalStorage;
    private String availableStorage;
    private String appList;
    private String systemAppList;
    private String sensorsInfo;
    private String adbEnabled;
    private String developmentSettingEnabled;
    private String httpProxy;
    private String dataRoaming;
    private String allowMockLocation;
    private String accessibilityEnabled;
    private String defaultInputMethod;
    private String touchExplorationEnabled;
    private String screenBrightness;
    private String screenOffTimeout;
    private String screenResolution;
    private double screenInches;
    private String filesAbsolutePath;
    private String packageName;
    private boolean harmonyOS;

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setVbMetaDigest(String vbMetaDigest) {
        this.vbMetaDigest = vbMetaDigest;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setBatteryHealthStatus(String batteryHealthStatus) {
        this.batteryHealthStatus = batteryHealthStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setBatteryTemp(String batteryTemp) {
        this.batteryTemp = batteryTemp;
    }

    public void setBatteryTotalCapacity(String batteryTotalCapacity) {
        this.batteryTotalCapacity = batteryTotalCapacity;
    }

    public void setCpuProcessor(String cpuProcessor) {
        this.cpuProcessor = cpuProcessor;
    }

    public void setCpuHardware(String cpuHardware) {
        this.cpuHardware = cpuHardware;
    }

    public void setCoresCount(String coresCount) {
        this.coresCount = coresCount;
    }

    public void setAbiType(String abiType) {
        this.abiType = abiType;
    }

    public void setDebug(int debug) {
        this.debug = debug;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public void setXposedStatus(boolean xposedStatus) {
        this.xposed = xposedStatus;
    }

    public void setMagiskStatus(boolean magiskStatus) {
        this.magisk = magiskStatus;
    }

    public void setHook(String hook) {
        this.hook = hook;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public void setGsfId(String gsfId) {
        this.gsfId = gsfId;
    }

    public void setMediaDrmId(String mediaDrmId) {
        this.mediaDrmId = mediaDrmId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public void setAvailableMemory(String availableMemory) {
        this.availableMemory = availableMemory;
    }

    public void setTotalStorage(String totalStorage) {
        this.totalStorage = totalStorage;
    }

    public void setAvailableStorage(String availableStorage) {
        this.availableStorage = availableStorage;
    }

    public void setAppList(String appList) {
        this.appList = appList;
    }

    public void setSystemAppList(String systemAppList) {
        this.systemAppList = systemAppList;
    }

    public void setSensorsInfo(String sensorsInfo) {
        this.sensorsInfo = sensorsInfo;
    }

    public void setAdbEnabled(String adbEnabled) {
        this.adbEnabled = adbEnabled;
    }

    public void setDevelopmentSettingEnabled(String developmentSettingEnabled) {
        this.developmentSettingEnabled = developmentSettingEnabled;
    }

    public void setHttpProxy(String httpProxy) {
        this.httpProxy = httpProxy;
    }

    public void setDataRoaming(String dataRoaming) {
        this.dataRoaming = dataRoaming;
    }

    public void setAllowMockLocation(String allowMockLocation) {
        this.allowMockLocation = allowMockLocation;
    }

    public void setAccessibilityEnabled(String accessibilityEnabled) {
        this.accessibilityEnabled = accessibilityEnabled;
    }

    public void setDefaultInputMethod(String defaultInputMethod) {
        this.defaultInputMethod = defaultInputMethod;
    }

    public void setTouchExplorationEnabled(String touchExplorationEnabled) {
        this.touchExplorationEnabled = touchExplorationEnabled;
    }

    public void setScreenBrightness(String screenBrightness) {
        this.screenBrightness = screenBrightness;
    }

    public void setScreenOffTimeout(String screenOffTimeout) {
        this.screenOffTimeout = screenOffTimeout;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public void setScreenInches(double screenInches) {
        this.screenInches = screenInches;
    }

    public void setFilesAbsolutePath(String filesAbsolutePath) {
        this.filesAbsolutePath = filesAbsolutePath;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setHarmonyOS(boolean harmony_OS) {
        this.harmonyOS = harmony_OS;
    }
}
