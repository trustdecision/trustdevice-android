package cn.tongdun.mobrisk.core.collectors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import cn.tongdun.mobrisk.core.tools.executeSafe

/**
 * @description: Battery Info
 * @author: wuzuchang
 * @date: 2023/5/12
 */
interface BatteryInfoInterface {
    fun getBatteryHealth(): String
    fun getBatteryStatus(): String
    fun getBatteryLevel(): Int
    fun getBatteryTemp(): Int
    fun getBatteryTotalCapacity(): String
}

private const val POWER_PROFILE_CLASS_NAME = "com.android.internal.os.PowerProfile"
private const val BATTERY_CAPACITY_METHOD_NAME = "getBatteryCapacity"

class BatteryInfoCollector(
    private val context: Context
) : BatteryInfoInterface {

    private var batteryHealth: Int? = 0
    private var batteryStatus: Int? = 0
    private var batteryLevel: Int? = 0
    private var batteryTemp: Int? = 0

    init {
        val intent: Intent? = context.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED),
            "com.permission.broadcast.td",
            null
        )
        batteryHealth = intent?.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
        batteryStatus = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, 0)
        batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        batteryTemp = intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
    }

    override fun getBatteryHealth(): String {
        val type: String = when (batteryHealth) {
            BatteryManager.BATTERY_HEALTH_GOOD -> "good"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "overheat"
            BatteryManager.BATTERY_HEALTH_COLD -> "cold"
            BatteryManager.BATTERY_HEALTH_DEAD -> "dead"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "over voltage"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "unspecified failure"
            else -> "unknown"
        }
        return type
    }

    override fun getBatteryStatus(): String {
        val statusStr: String = when (batteryStatus) {
            BatteryManager.BATTERY_STATUS_CHARGING -> "charging"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "discharging"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "not charging"
            BatteryManager.BATTERY_STATUS_FULL -> "full"
            else -> "unknown"
        }
        return statusStr
    }

    override fun getBatteryLevel(): Int = batteryLevel?:0

    override fun getBatteryTemp(): Int = batteryTemp?:0

    @SuppressLint("PrivateApi")
    override fun getBatteryTotalCapacity(): String {
        return executeSafe({
            val mPowerProfile = Class.forName(POWER_PROFILE_CLASS_NAME)
                .getConstructor(Context::class.java)
                .newInstance(context)
            val batteryCapacity = Class.forName(POWER_PROFILE_CLASS_NAME)
                .getMethod(BATTERY_CAPACITY_METHOD_NAME)
                .invoke(mPowerProfile) as Double

            batteryCapacity.toString()
        }, "")
    }

}
