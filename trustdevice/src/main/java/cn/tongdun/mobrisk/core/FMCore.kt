package cn.tongdun.mobrisk.core

import android.app.ActivityManager
import android.content.Context
import android.hardware.SensorManager
import cn.tongdun.mobrisk.TDRiskCallback
import cn.tongdun.mobrisk.TDRiskOption
import cn.tongdun.mobrisk.beans.DeviceInfo
import cn.tongdun.mobrisk.core.collectors.*
import cn.tongdun.mobrisk.core.tools.DeviceInfoUtils
import cn.tongdun.mobrisk.core.collectors.SettingInfoCollector
import cn.tongdun.mobrisk.core.tools.executeSafe
import org.json.JSONObject
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class FMCore private constructor() {

    companion object {
        init {
            executeSafe { System.loadLibrary("tddevicefingerprint") }
        }

        val instance: FMCore by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FMCore()
        }
    }


    private val executor = Executors.newSingleThreadExecutor()
    private val countDownLatch = CountDownLatch(1)
    private lateinit var mContext: Context
    private lateinit var mDeviceInfo: DeviceInfo
    private lateinit var mDeviceId: String
    private var mCallback: TDRiskCallback? = null

    fun init(context: Context, option: TDRiskOption) {
        mContext = context.applicationContext
        mDeviceInfo = DeviceInfo()
        mCallback = option.callback
        executor.execute {
            collectorDeviceId()
            collectorDebugInfo()
            collectorRoot()
            collectorXposed()
            collectorMagisk()
            collectorEmulator()
            collectorHook()
            collectorVpn()
            collectorDeviceInfoTampered()
            collectorBuildInfo()
            collectorDeviceBaseInfo()
            collectorDevicePersonalizationInfo()
            collectorMemoryInfo()
            collectorCpuInfo()
            collectorBatteryInfo()
            collectorSettingInfo()
            collectorSensorInfo()
            collectorPackageListInfo()
            countDownLatch.countDown()
            mCallback?.onEvent(getDeviceInfo())
        }
    }

    private fun collectorDeviceId() {
        val deviceIdCollector = DeviceIdCollector(mContext.contentResolver)
        mDeviceId = deviceIdCollector.getDeviceId()
        mDeviceInfo.androidId = deviceIdCollector.getAndroidId()
        mDeviceInfo.gsfId = deviceIdCollector.getGsfId()
        mDeviceInfo.mediaDrmId = deviceIdCollector.getMediaDrmId()
        mDeviceInfo.vbMetaDigest = deviceIdCollector.getVbMetaDigest()
        mDeviceInfo.googleAdId = deviceIdCollector.getGoogleAdid(mContext)
    }

    private fun collectorDebugInfo() {
        val debugInfoCollector = DebugInfoCollector()
        mDeviceInfo.debug = debugInfoCollector.getDebug()
    }

    private fun collectorRoot() {
        val rootCollector = RootCollector()
        mDeviceInfo.root = rootCollector.getRoot()
    }

    private fun collectorXposed() {
        val xposedCollector = XposedCollector()
        mDeviceInfo.xposed = xposedCollector.findXposed()
    }

    private fun collectorMagisk() {
        val magiskCollector = MagiskCollector()
        mDeviceInfo.magisk = magiskCollector.detectMagisk()
    }

    private fun collectorHook() {
        val hookCollector = HookCollector()
        mDeviceInfo.hook = hookCollector.detectHook()
    }

    private fun collectorEmulator() {
        val emulatorCollector = EmulatorCollector()
        mDeviceInfo.emulator = emulatorCollector.detectEmulator()
    }

    private fun collectorVpn() {
        val vpnCollector = VpnCollector()
        mDeviceInfo.vpn = vpnCollector.detectVpn(mContext)
    }

    private fun collectorDeviceInfoTampered() {
        val deviceIdCollector = DeviceInfoTamperedCollector()
        mDeviceInfo.deviceInfoTampered = deviceIdCollector.tampered(mContext)
    }

    private fun collectorBuildInfo() {
        val buildInfoCollector = BuildInfoCollector()
        mDeviceInfo.model = buildInfoCollector.getModel()
        mDeviceInfo.manufacturer = buildInfoCollector.getManufacturer()
        mDeviceInfo.androidVersion = buildInfoCollector.getAndroidVersion()
        mDeviceInfo.sdkVersion = buildInfoCollector.getSdkVersion()
        mDeviceInfo.kernelVersion = buildInfoCollector.getKernelVersion()
        mDeviceInfo.fingerprint = buildInfoCollector.getFingerprint()
        mDeviceInfo.hardware = buildInfoCollector.getHardware()
        mDeviceInfo.product = buildInfoCollector.getProduct()
        mDeviceInfo.brand = buildInfoCollector.getBrand()
        mDeviceInfo.host = buildInfoCollector.getHost()
        mDeviceInfo.display = buildInfoCollector.getDisplay()
    }

    private fun collectorDeviceBaseInfo() {
        val deviceBaseInfoCollector = DeviceBaseInfoCollector(mContext)
        mDeviceInfo.screenResolution = deviceBaseInfoCollector.getScreenResolution()
        mDeviceInfo.screenInches = deviceBaseInfoCollector.getScreenInches()
        mDeviceInfo.filesAbsolutePath = deviceBaseInfoCollector.getFilesAbsolutePath()
        mDeviceInfo.packageName = deviceBaseInfoCollector.getPackageName()
        mDeviceInfo.harmonyOS = deviceBaseInfoCollector.isHarmonyOS()
    }

    private fun collectorDevicePersonalizationInfo() {
        val devicePersonalizationInfoCollector = DevicePersonalizationInfoCollector()
        mDeviceInfo.country = devicePersonalizationInfoCollector.getLocaleCountry()
        mDeviceInfo.language = devicePersonalizationInfoCollector.getDefaultLanguage()
        mDeviceInfo.timezone = devicePersonalizationInfoCollector.getTimezone()
    }

    private fun collectorMemoryInfo() {
        val activityManager = mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfoCollector = MemoryInfoCollector(activityManager)
        mDeviceInfo.totalMemory = memoryInfoCollector.getTotalMemory().toString()
        mDeviceInfo.availableMemory = memoryInfoCollector.getAvailableMemory().toString()
        mDeviceInfo.totalStorage = memoryInfoCollector.getTotalStorage().toString()
        mDeviceInfo.availableStorage = memoryInfoCollector.getAvailableStorage().toString()
    }

    private fun collectorCpuInfo() {
        val cpuInfoCollector = CpuInfoCollector()
        mDeviceInfo.coresCount = cpuInfoCollector.availableProcessors()
        mDeviceInfo.cpuHardware = cpuInfoCollector.getCpuHardware()
        mDeviceInfo.cpuProcessor = cpuInfoCollector.getCpuProcessor()
        mDeviceInfo.abiType = cpuInfoCollector.getAbiType()
    }

    private fun collectorBatteryInfo() {
        val batteryInfoCollector = BatteryInfoCollector(mContext)
        mDeviceInfo.batteryHealthStatus = batteryInfoCollector.getBatteryHealth()
        mDeviceInfo.batteryStatus = batteryInfoCollector.getBatteryStatus()
        mDeviceInfo.batteryLevel = batteryInfoCollector.getBatteryLevel().toString()
        mDeviceInfo.batteryTemp = batteryInfoCollector.getBatteryTemp().toString()
        mDeviceInfo.batteryTotalCapacity = batteryInfoCollector.getBatteryTotalCapacity()
    }

    private fun collectorSettingInfo() {
        val settingInfoCollector = SettingInfoCollector(mContext.contentResolver)
        mDeviceInfo.adbEnabled = settingInfoCollector.getAdbEnabled()
        mDeviceInfo.developmentSettingEnabled = settingInfoCollector.getDevelopmentSettingEnabled()
        mDeviceInfo.httpProxy = settingInfoCollector.getHttpProxy()
        mDeviceInfo.dataRoaming = settingInfoCollector.getDataRoaming()
        mDeviceInfo.allowMockLocation = settingInfoCollector.getAllowMockLocation()
        mDeviceInfo.accessibilityEnabled = settingInfoCollector.getAccessibilityEnabled()
        mDeviceInfo.defaultInputMethod = settingInfoCollector.getDefaultInputMethod()
        mDeviceInfo.touchExplorationEnabled = settingInfoCollector.getTouchExplorationEnabled()
        mDeviceInfo.screenBrightness = settingInfoCollector.getScreenBrightness()
        mDeviceInfo.screenOffTimeout = settingInfoCollector.getScreenOffTimeout()
    }

    private fun collectorSensorInfo() {
        val sensorManager = mContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorsInfoCollector = SensorsInfoCollector(sensorManager)
        mDeviceInfo.sensorsInfo = sensorsInfoCollector.getSensorList()
    }

    private fun collectorPackageListInfo() {
        val appListCollector = AppListCollector(mContext.packageManager)
        mDeviceInfo.appList = appListCollector.getAppList()
        mDeviceInfo.systemAppList = appListCollector.getSystemAppList()
    }

    fun getDeviceInfo(): JSONObject {
        executeSafe { countDownLatch.await(1000L, TimeUnit.MILLISECONDS) }
        return DeviceInfoUtils.format(mDeviceId, mDeviceInfo)
    }
}