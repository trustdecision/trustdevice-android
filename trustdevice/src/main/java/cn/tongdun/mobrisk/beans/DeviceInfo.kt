package cn.tongdun.mobrisk.beans

/**
 * @description: Device info
 * @author: wuzuchang
 * @date: 2023/5/12
 */
class DeviceInfo {
    var androidId: String? = null
    var gsfId: String? = null
    var mediaDrmId: String? = null
    var vbMetaDigest: String? = null
    var googleAdId: String? = null
    var model: String? = null
    var manufacturer: String? = null
    var androidVersion: String? = null
    var sdkVersion: String? = null
    var kernelVersion: String? = null
    var fingerprint: String? = null
    var hardware: String? = null
    var product: String? = null
    var brand: String? = null
    var display: String? = null
    var host: String? = null
    var batteryHealthStatus: String? = null
    var batteryStatus: String? = null
    var batteryLevel: String? = null
    var batteryTemp: String? = null
    var batteryTotalCapacity: String? = null
    var cpuProcessor: String? = null
    var cpuHardware: String? = null
    var coresCount: String? = null
    var abiType: String? = null
    var debug = 0
    var root = false
    var xposed = false
    var magisk = false
    var emulator = false
    var hook: String? = null
    var country: String? = null
    var language: String? = null
    var timezone: String? = null
    var totalMemory: String? = null
    var availableMemory: String? = null
    var totalStorage: String? = null
    var availableStorage: String? = null
    var appList: String? = null
    var systemAppList: String? = null
    var sensorsInfo: String? = null
    var adbEnabled: String? = null
    var developmentSettingEnabled: String? = null
    var httpProxy: String? = null
    var dataRoaming: String? = null
    var allowMockLocation: String? = null
    var accessibilityEnabled: String? = null
    var defaultInputMethod: String? = null
    var touchExplorationEnabled: String? = null
    var screenBrightness: String? = null
    var screenOffTimeout: String? = null
    var screenResolution: String? = null
    var screenInches = 0.0
    var filesAbsolutePath: String? = null
    var packageName: String? = null
    var harmonyOS = false
}