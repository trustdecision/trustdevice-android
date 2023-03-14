package cn.tongdun.mobrisk.core;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.hardware.SensorManager;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.tongdun.mobrisk.TDRiskCallback;
import cn.tongdun.mobrisk.TDRiskOption;
import cn.tongdun.mobrisk.beans.DeviceInfo;
import cn.tongdun.mobrisk.core.collectors.AppListCollector;
import cn.tongdun.mobrisk.core.collectors.BatteryInfoCollector;
import cn.tongdun.mobrisk.core.collectors.BuildInfoCollector;
import cn.tongdun.mobrisk.core.collectors.CpuInfoCollector;
import cn.tongdun.mobrisk.core.collectors.DebugInfoCollector;
import cn.tongdun.mobrisk.core.collectors.DeviceBaseInfoCollector;
import cn.tongdun.mobrisk.core.collectors.DeviceIdCollector;
import cn.tongdun.mobrisk.core.collectors.DevicePersonalizationInfoCollector;
import cn.tongdun.mobrisk.core.collectors.MagiskCollector;
import cn.tongdun.mobrisk.core.collectors.MemoryInfoCollector;
import cn.tongdun.mobrisk.core.collectors.RootCollector;
import cn.tongdun.mobrisk.core.collectors.SensorsInfoCollector;
import cn.tongdun.mobrisk.core.collectors.SettingInfoCollector;
import cn.tongdun.mobrisk.core.collectors.XposedCollector;
import cn.tongdun.mobrisk.core.utils.DeviceInfoUtils;
import cn.tongdun.mobrisk.core.utils.LogUtils;


public class FMCore {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private Context mContext;
    private DeviceInfo mDeviceInfo;
    private String mDeviceId;
    private TDRiskCallback mCallback = null;

    private FMCore() {
    }

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static final FMCore instance = new FMCore();
    }

    public static FMCore getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context context, TDRiskOption option) {
        if (context == null) {
            return;
        }
        if (mDeviceInfo == null) {
            mDeviceInfo = new DeviceInfo();
        }
        if (option != null) {
            mCallback = option.getCallback();
        }
        mContext = context.getApplicationContext();
        executor.execute(() -> {
            collectorDeviceId();
            collectorDebugInfo();
            collectorRoot();
            collectorXposed();
            collectorMagisk();
            collectorBuildInfo();
            collectorDeviceBaseInfo();
            collectorDevicePersonalizationInfo();
            collectorMemoryInfo();
            collectorCpuInfo();
            collectorBatteryInfo();
            collectorSettingInfo();
            collectorSensorInfo();
            collectorPackageListInfo();
            countDownLatch.countDown();
            if (mCallback !=null){
                mCallback.onEvent(getDeviceInfo());
            }
        });
    }

    private void collectorDeviceId() {
        DeviceIdCollector deviceIdCollector = new DeviceIdCollector(mContext.getContentResolver());
        mDeviceId = deviceIdCollector.getDeviceId();
        mDeviceInfo.setAndroidId(deviceIdCollector.getAndroidId());
        mDeviceInfo.setGsfId(deviceIdCollector.getGsfId());
        mDeviceInfo.setMediaDrmId(deviceIdCollector.getMediaDrmId());
        mDeviceInfo.setVbMetaDigest(deviceIdCollector.getVbMetaDigest());
    }

    private void collectorDebugInfo() {
        DebugInfoCollector debugInfoCollector = new DebugInfoCollector();
        mDeviceInfo.setDebug(debugInfoCollector.getDebug());
    }

    private void collectorRoot() {
        RootCollector rootCollector = new RootCollector();
        mDeviceInfo.setRoot(rootCollector.getRoot());
    }

    private void collectorXposed() {
        XposedCollector xposedCollector = new XposedCollector();
        mDeviceInfo.setXposedStatus(xposedCollector.findXposed());
    }

    private void collectorMagisk() {
        MagiskCollector magiskCollector = new MagiskCollector();
        mDeviceInfo.setMagiskStatus(magiskCollector.detectMagisk());
    }

    private void collectorBuildInfo() {
        BuildInfoCollector buildInfoCollector = new BuildInfoCollector();
        mDeviceInfo.setModel(buildInfoCollector.getModel());
        mDeviceInfo.setManufacturer(buildInfoCollector.getManufacturer());
        mDeviceInfo.setAndroidVersion(buildInfoCollector.getAndroidVersion());
        mDeviceInfo.setSdkVersion(buildInfoCollector.getSdkVersion());
        mDeviceInfo.setKernelVersion(buildInfoCollector.getKernelVersion());
        mDeviceInfo.setFingerprint(buildInfoCollector.getFingerprint());
        mDeviceInfo.setHardware(buildInfoCollector.getHardware());
        mDeviceInfo.setProduct(buildInfoCollector.getProduct());
        mDeviceInfo.setBrand(buildInfoCollector.getBrand());
        mDeviceInfo.setHost(buildInfoCollector.getHost());
        mDeviceInfo.setDisplay(buildInfoCollector.getDisplay());
        mDeviceInfo.setHost(buildInfoCollector.getHost());
    }

    private void collectorDeviceBaseInfo() {
        DeviceBaseInfoCollector deviceBaseInfoCollector = new DeviceBaseInfoCollector(mContext);
        mDeviceInfo.setScreenResolution(deviceBaseInfoCollector.getScreenResolution());
        mDeviceInfo.setScreenInches(deviceBaseInfoCollector.getScreenInches());
        mDeviceInfo.setFilesAbsolutePath(deviceBaseInfoCollector.getFilesAbsolutePath());
        mDeviceInfo.setPackageName(deviceBaseInfoCollector.getPackageName());
        mDeviceInfo.setHarmonyOS(deviceBaseInfoCollector.isHarmonyOS());
    }

    private void collectorDevicePersonalizationInfo() {
        DevicePersonalizationInfoCollector devicePersonalizationInfoCollector = new DevicePersonalizationInfoCollector();
        mDeviceInfo.setCountry(devicePersonalizationInfoCollector.getLocaleCountry());
        mDeviceInfo.setLanguage(devicePersonalizationInfoCollector.getDefaultLanguage());
        mDeviceInfo.setTimezone(devicePersonalizationInfoCollector.getTimezone());
    }

    private void collectorMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfoCollector memoryInfoCollector = new MemoryInfoCollector(activityManager);
        mDeviceInfo.setTotalMemory(String.valueOf(memoryInfoCollector.getTotalMemory()));
        mDeviceInfo.setAvailableMemory(String.valueOf(memoryInfoCollector.getAvailableMemory()));
        mDeviceInfo.setTotalStorage(String.valueOf(memoryInfoCollector.getTotalStorage()));
        mDeviceInfo.setAvailableStorage(String.valueOf(memoryInfoCollector.getAvailableStorage()));
    }

    private void collectorCpuInfo() {
        CpuInfoCollector cpuInfoCollector = new CpuInfoCollector();
        mDeviceInfo.setCoresCount(cpuInfoCollector.availableProcessors());
        mDeviceInfo.setCpuHardware(cpuInfoCollector.getCpuHardware());
        mDeviceInfo.setCpuProcessor(cpuInfoCollector.getCpuProcessor());
        mDeviceInfo.setAbiType(cpuInfoCollector.getAbiType());
    }

    private void collectorBatteryInfo() {
        BatteryInfoCollector batteryInfoCollector = new BatteryInfoCollector(mContext);
        mDeviceInfo.setBatteryHealthStatus(batteryInfoCollector.getBatteryHealthStatus());
        mDeviceInfo.setBatteryStatus(batteryInfoCollector.getBatteryStatus());
        mDeviceInfo.setBatteryLevel(String.valueOf(batteryInfoCollector.getBatteryLevel()));
        mDeviceInfo.setBatteryTemp(String.valueOf(batteryInfoCollector.getBatteryTemp()));
        mDeviceInfo.setBatteryTotalCapacity(batteryInfoCollector.getBatteryTotalCapacity());
    }

    private void collectorSettingInfo() {
        SettingInfoCollector settingInfoCollector = new SettingInfoCollector(mContext.getContentResolver());
        mDeviceInfo.setAdbEnabled(settingInfoCollector.getAdbEnabled());
        mDeviceInfo.setDevelopmentSettingEnabled(settingInfoCollector.getDevelopmentSettingEnabled());
        mDeviceInfo.setHttpProxy(settingInfoCollector.getHttpProxy());
        mDeviceInfo.setDataRoaming(settingInfoCollector.getDataRoaming());
        mDeviceInfo.setAllowMockLocation(settingInfoCollector.getAllowMockLocation());
        mDeviceInfo.setAccessibilityEnabled(settingInfoCollector.getAccessibilityEnabled());
        mDeviceInfo.setDefaultInputMethod(settingInfoCollector.getDefaultInputMethod());
        mDeviceInfo.setTouchExplorationEnabled(settingInfoCollector.getTouchExplorationEnabled());
        mDeviceInfo.setScreenBrightness(settingInfoCollector.getScreenBrightness());
        mDeviceInfo.setScreenOffTimeout(settingInfoCollector.getScreenOffTimeout());
    }

    private void collectorSensorInfo() {
        SensorManager sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        SensorsInfoCollector sensorsInfoCollector = new SensorsInfoCollector(sensorManager);
        mDeviceInfo.setSensorsInfo(sensorsInfoCollector.getSensorList());
    }

    private void collectorPackageListInfo() {
        AppListCollector appListCollector = new AppListCollector(mContext.getPackageManager());
        mDeviceInfo.setAppList(appListCollector.getAppList());
        mDeviceInfo.setSystemAppList(appListCollector.getSystemAppList());
    }

    public JSONObject getDeviceInfo() {
        try {
            countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            LogUtils.e("getDeviceInfo await exception e:" + e.getMessage());
        }
        return DeviceInfoUtils.format(mDeviceId, mDeviceInfo);
    }
}
