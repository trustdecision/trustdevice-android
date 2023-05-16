package cn.tongdun.android.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cn.tongdun.android.activitys.ItemListActivity
import cn.tongdun.android.adapters.DeviceInfoItemAdapter
import cn.tongdun.mobrisk.TDRisk
import cn.tongdun.mobrisk.providers.DeviceInfoProvider
import com.trustdevice.android.R
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * @description:DeviceId Fragment
 * @author: wuzuchang
 * @date: 2023/2/28
 */
@SuppressLint("SetTextI18n")
class DeviceInfoFragment : Fragment() {
    //BuildInfo
    @BindView(R.id.tv_brand)
    lateinit var mTvBrand: TextView

    @BindView(R.id.tv_model)
    lateinit var mTvModel: TextView

    @BindView(R.id.rv_build_info)
    lateinit var mRcBuildInfo: RecyclerView

    //Setting
    @BindView(R.id.rv_setting)
    lateinit var mRcSetting: RecyclerView

    // MemoryIndo
    @BindView(R.id.tv_internal_storage_path)
    lateinit var mTvInternalStoragePath: TextView

    @BindView(R.id.internal_storage_progress)
    lateinit var mPbInternalStorageProgress: ProgressBar

    @BindView(R.id.tv_internal_storage_scale)
    lateinit var mTvInternalStorageScale: TextView

    @BindView(R.id.tv_internal_storage_info)
    lateinit var mTvInternalStorageInfo: TextView

    @BindView(R.id.tv_ram_path)
    lateinit var mTvRAMPath: TextView

    @BindView(R.id.ram_progress)
    lateinit var mPbRAMProgress: ProgressBar

    @BindView(R.id.tv_ram_scale)
    lateinit var mTvRAMScale: TextView

    @BindView(R.id.tv_ram_info)
    lateinit var mTvRAMInfo: TextView

    // Battery
    @BindView(R.id.tv_battery_status)
    lateinit var mTvBatterStatus: TextView

    @BindView(R.id.tv_battery_health)
    lateinit var mTvBatterHealth: TextView

    @BindView(R.id.battery_progress)
    lateinit var mPbBatteryProgress: ProgressBar

    @BindView(R.id.tv_battery_power)
    lateinit var mTvBatteryPower: TextView

    @BindView(R.id.tv_battery_info)
    lateinit var mTvBatteryInfo: TextView

    // Apps
    @BindView(R.id.tv_app_total)
    lateinit var mTvAppTotal: TextView

    @BindView(R.id.all_apps_layout)
    lateinit var mAppLayout: ConstraintLayout

    //Sensor
    @BindView(R.id.tv_sensor_total)
    lateinit var mTvSensorTotal: TextView

    @BindView(R.id.all_sensor_layout)
    lateinit var mSensorLayout: ConstraintLayout
    lateinit var deviceInfoProvider: DeviceInfoProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deviceInfo = TDRisk.getBlackbox()
        deviceInfoProvider = DeviceInfoProvider(deviceInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_device_info, container, false)
        ButterKnife.bind(this, rootView)
        initView()
        return rootView
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

    private fun initBuildInfoView() {
        mTvBrand.text = deviceInfoProvider.getBuildInfo().brand
        mTvModel.text = deviceInfoProvider.getBuildInfo().model
        val manager = LinearLayoutManager(activity)
        manager.orientation = GridLayoutManager.VERTICAL
        mRcBuildInfo.layoutManager = manager
        val buildData = deviceInfoProvider.getBuildInfo().getRawData()
        val deviceInfoItemAdapter = DeviceInfoItemAdapter(buildData)
        mRcBuildInfo.adapter = deviceInfoItemAdapter
    }

    private fun initSettingView() {
        val manager = LinearLayoutManager(activity)
        manager.orientation = GridLayoutManager.VERTICAL
        mRcSetting.layoutManager = manager
        val buildData = deviceInfoProvider.getCpuInfo().getRawData()
        val deviceInfoItemAdapter = DeviceInfoItemAdapter(buildData)
        mRcSetting.adapter = deviceInfoItemAdapter
    }

    private fun initInternalStorageView() {
        mTvInternalStoragePath.text = Environment.getDataDirectory().path
        val totalStorage = deviceInfoProvider.getMemoryInfo().totalStorage / Math.pow(1024.0, 3.0)
        val availableStorage =
            deviceInfoProvider.getMemoryInfo().availableStorage / Math.pow(1024.0, 3.0)
        val useStorage = totalStorage - availableStorage
        val internalStorageInfo =
            String.format(Locale.US, "%.2fGB", useStorage) + " / " + String.format(
                Locale.US, "%.2fGB", totalStorage
            ) + ", Free: " + String.format(Locale.US, "%.2fGB", availableStorage)
        mTvInternalStorageInfo.text = internalStorageInfo
        val scale = 100 / totalStorage
        mPbInternalStorageProgress.progress = (useStorage * scale).toInt()
        mTvInternalStorageScale.text =
            String.format(Locale.US, "%.2f", useStorage / totalStorage * 100) + "%"
    }

    private fun initRAMView() {
        mTvRAMPath.text = "The state at the time of collection"
        val totalMemory = deviceInfoProvider.getMemoryInfo().totalMemory / Math.pow(1024.0, 3.0)
        val availableMemory =
            deviceInfoProvider.getMemoryInfo().availableMemory / Math.pow(1024.0, 3.0)
        val useMemory = totalMemory - availableMemory
        val internalStorageInfo =
            String.format(Locale.US, "%.2fGB", useMemory) + " / " + String.format(
                Locale.US, "%.2fGB", totalMemory
            ) + ", Free: " + String.format(Locale.US, "%.2fGB", availableMemory)
        mTvRAMInfo.text = internalStorageInfo
        val scale = 100 / totalMemory
        mPbRAMProgress.progress = (useMemory * scale).toInt()
        mTvRAMScale.text = String.format(Locale.US, "%.2f", useMemory / totalMemory * 100) + "%"
    }

    private fun initBatteryView() {
        mTvBatterStatus.text = "(" + deviceInfoProvider.getBatteryInfo().status + ")"
        mTvBatterHealth.text =
            "total capacity: " + deviceInfoProvider.getBatteryInfo().TotalCapacity + "mAh"
        val power = deviceInfoProvider.getBatteryInfo().level
        mPbBatteryProgress.progress = power
        mTvBatteryPower.text = "$power%"
        val temp = deviceInfoProvider.getBatteryInfo().temp
        val temperature =
            BigDecimal.valueOf((temp.toFloat() / 10).toDouble()).setScale(2, RoundingMode.HALF_UP)
                .toDouble()
        mTvBatteryInfo.text =
            "health: " + deviceInfoProvider.getBatteryInfo().health + ", temperature: " + temperature + "℃"
    }

    private fun initAppTotalView() {
        val userAppTotal = deviceInfoProvider.getAppListInfo().appList.split(",".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray().size
        val systemAppTotal =
            deviceInfoProvider.getAppListInfo().systemAppList.split(",".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray().size
        val total = userAppTotal + systemAppTotal
        mTvAppTotal.text = total.toString() + ""
    }

    private fun initSensorTotalView() {
        val total = deviceInfoProvider.getSensorInfo().getSensorTotal()
        mTvSensorTotal.text = total.toString() + ""
    }

    @OnClick(R.id.all_apps_layout, R.id.all_sensor_layout)
    fun onViewClick(view: View) {
        when (view.id) {
            R.id.all_apps_layout -> jumpToListActivity(0)
            R.id.all_sensor_layout -> jumpToListActivity(1)
        }
    }

    private fun jumpToListActivity(type: Int) {
        val sensor = Intent(activity, ItemListActivity::class.java)
        sensor.putExtra("type", type)
        startActivity(sensor)
    }
}