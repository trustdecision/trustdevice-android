package cn.tongdun.android.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.tongdun.android.activitys.ItemListActivity
import cn.tongdun.android.adapters.DeviceInfoItemAdapter
import cn.tongdun.android.base.BaseFragment
import cn.tongdun.mobrisk.TDRisk
import cn.tongdun.mobrisk.providers.DeviceInfoProvider
import com.trustdevice.android.R
import com.trustdevice.android.databinding.FragmentDeviceInfoBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * @description:DeviceId Fragment
 * @author: wuzuchang
 * @date: 2023/2/28
 */
@SuppressLint("SetTextI18n")
class DeviceInfoFragment : BaseFragment<FragmentDeviceInfoBinding>() {
    lateinit var deviceInfoProvider: DeviceInfoProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deviceInfo = TDRisk.getBlackbox()
        deviceInfoProvider = DeviceInfoProvider(deviceInfo)
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentDeviceInfoBinding = FragmentDeviceInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }

    private fun initView() {
        initBuildInfoView()
        initSettingView()
        initInternalStorageView()
        initRAMView()
        initBatteryView()
        initAppTotalView()
        initSensorTotalView()
    }

    private fun initEvent() {
        binding.allAppsLayout.appListRoot.setOnClickListener {
            jumpToListActivity(0)
        }
        binding.allSensorLayout.sensorListRoot.setOnClickListener {
            jumpToListActivity(1)
        }
    }

    private fun initBuildInfoView() {
        binding.buildInfoLayout.apply {
            tvBrand.text = deviceInfoProvider.getBuildInfo().brand
            tvModel.text = deviceInfoProvider.getBuildInfo().model
            val manager = LinearLayoutManager(activity)
            manager.orientation = GridLayoutManager.VERTICAL
            rvBuildInfo.layoutManager = manager
            val buildData = deviceInfoProvider.getBuildInfo().getRawData()
            val deviceInfoItemAdapter = DeviceInfoItemAdapter(buildData)
            rvBuildInfo.adapter = deviceInfoItemAdapter
        }
    }

    private fun initSettingView() {
        binding.settingLayout.apply {
            val manager = LinearLayoutManager(activity)
            manager.orientation = GridLayoutManager.VERTICAL
            rvSetting.layoutManager = manager
            val buildData = deviceInfoProvider.getCpuInfo().getRawData()
            val deviceInfoItemAdapter = DeviceInfoItemAdapter(buildData)
            rvSetting.adapter = deviceInfoItemAdapter
        }
    }

    private fun initInternalStorageView() {
        binding.memoryInfoLayout.apply {
            tvInternalStoragePath.text = Environment.getDataDirectory().path
            val totalStorage =
                deviceInfoProvider.getMemoryInfo().totalStorage / Math.pow(1024.0, 3.0)
            val availableStorage =
                deviceInfoProvider.getMemoryInfo().availableStorage / Math.pow(1024.0, 3.0)
            val useStorage = totalStorage - availableStorage
            val freeText = getString(R.string.free_text)
            val internalStorageInfo =
                String.format(Locale.US, "%.2fGB", useStorage) + " / " + String.format(
                    Locale.US, "%.2fGB", totalStorage
                ) + ", ${freeText}: " + String.format(Locale.US, "%.2fGB", availableStorage)

            tvInternalStorageInfo.text = internalStorageInfo
            val scale = 100 / totalStorage
            internalStorageProgress.progress = (useStorage * scale).toInt()
            tvInternalStorageScale.text =
                String.format(Locale.US, "%.2f", useStorage / totalStorage * 100) + "%"
        }

    }

    private fun initRAMView() {
        binding.memoryInfoLayout.apply {
            tvRamPath.text = getString(R.string.memory_desc)
            val totalMemory = deviceInfoProvider.getMemoryInfo().totalMemory / Math.pow(1024.0, 3.0)
            val availableMemory =
                deviceInfoProvider.getMemoryInfo().availableMemory / Math.pow(1024.0, 3.0)
            val useMemory = totalMemory - availableMemory
            val freeText = getString(R.string.free_text)
            val internalStorageInfo =
                String.format(Locale.US, "%.2fGB", useMemory) + " / " + String.format(
                    Locale.US, "%.2fGB", totalMemory
                ) + ", ${freeText}: " + String.format(Locale.US, "%.2fGB", availableMemory)
            tvRamInfo.text = internalStorageInfo
            val scale = 100 / totalMemory
            ramProgress.progress = (useMemory * scale).toInt()
            tvRamScale.text = String.format(Locale.US, "%.2f", useMemory / totalMemory * 100) + "%"
        }

    }

    private fun initBatteryView() {
        binding.batteryInfoLayout.apply {
            tvBatteryStatus.text = "(" + deviceInfoProvider.getBatteryInfo().status + ")"
            val totalCapacityText = getString(R.string.total_capacity_text)
            tvBatteryHealth.text =
                "${totalCapacityText}: " + deviceInfoProvider.getBatteryInfo().totalCapacity + "mAh"
            val power = deviceInfoProvider.getBatteryInfo().level

            batteryProgress.progress = power
            tvBatteryPower.text = "$power%"
            val temp = deviceInfoProvider.getBatteryInfo().temp
            val temperature = BigDecimal.valueOf((temp.toFloat() / 10).toDouble())
                .setScale(2, RoundingMode.HALF_UP).toDouble()
            val healthText = getString(R.string.health_text)
            val temperatureText = getString(R.string.temperature_text)
            tvBatteryInfo.text =
                "${healthText}: " + deviceInfoProvider.getBatteryInfo().health + ", ${temperatureText}: " + temperature + "℃"
        }

    }

    private fun initAppTotalView() {
        binding.allAppsLayout.apply {
            val userAppTotal = deviceInfoProvider.getAppListInfo().appList.split(",".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray().size
            val systemAppTotal =
                deviceInfoProvider.getAppListInfo().systemAppList.split(",".toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray().size
            val total = userAppTotal + systemAppTotal
            tvAppTotal.text = total.toString() + ""
        }
    }

    private fun initSensorTotalView() {
        binding.allSensorLayout.apply {
            val total = deviceInfoProvider.getSensorInfo().getSensorTotal()
            tvSensorTotal.text = total.toString() + ""
        }
    }

    private fun jumpToListActivity(type: Int) {
        val sensor = Intent(activity, ItemListActivity::class.java)
        sensor.putExtra("type", type)
        startActivity(sensor)
    }
}