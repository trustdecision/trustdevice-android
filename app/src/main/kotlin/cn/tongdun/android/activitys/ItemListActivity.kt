package cn.tongdun.android.activitys

import android.content.pm.ApplicationInfo
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import cn.tongdun.android.adapters.AppListRecyclerViewAdapter
import cn.tongdun.android.base.BaseActivity
import cn.tongdun.android.beans.AppItemData
import cn.tongdun.mobrisk.TDRisk.getBlackbox
import cn.tongdun.mobrisk.providers.DeviceInfoProvider
import com.trustdevice.android.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ItemListActivity : BaseActivity() {
    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView

    @BindView(R.id.tv_title_right)
    lateinit  var tvTitleRight: TextView

    @BindView(R.id.rv_item_list)
    lateinit var rvItemList: RecyclerView
    private var mType = 0
    private var mShowSystemApp = false
    private val mItemData: MutableList<AppItemData> = ArrayList()
    private var mAdapter: AppListRecyclerViewAdapter? = null

    override val contentViewResId: Int
        get() = R.layout.activity_app_list

    override fun initData() {
        mType = intent.getIntExtra("type", -1)
        val deviceInfo = getBlackbox()
        if (mType == 0) {
            loadInstalledAppList(mShowSystemApp)
        } else if (mType == 1) {
            loadSensorList(deviceInfo)
        }
    }

    override fun initView() {
        if (mType == 0) {
            tvTitle.text = resources.getString(R.string.app_list)
            tvTitleRight.text = resources.getString(R.string.show_system_apps)
        } else if (mType == 1) {
            tvTitle.text = resources.getString(R.string.sensor_list)
            tvTitleRight.visibility = View.GONE
        }
        val manager = LinearLayoutManager(this)
        rvItemList.layoutManager = manager
        mAdapter = AppListRecyclerViewAdapter(mItemData)
        rvItemList.adapter = mAdapter
        rvItemList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    @OnClick(R.id.tv_title, R.id.tv_title_right)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.tv_title -> finish()
            R.id.tv_title_right -> {
                mShowSystemApp = !mShowSystemApp
                if (mShowSystemApp) {
                    tvTitleRight.text = resources.getString(R.string.hide_system_apps)
                } else {
                    tvTitleRight.text = resources.getString(R.string.show_system_apps)
                }
                loadInstalledAppList(mShowSystemApp)
                mAdapter?.updateData(mItemData)
            }
        }
    }

    private fun loadInstalledAppList(showSystemApp: Boolean) {
        val packageManager = packageManager ?: return
        val packageInfoList = packageManager.getInstalledPackages(0)
        for (info in packageInfoList) {
            if (info == null) {
                continue
            }
            if (!showSystemApp && isSystemApp(info.applicationInfo)) {
                continue
            }
            val packageName = info.packageName
            val versionName = info.versionName
            val icon = info.applicationInfo.loadIcon(packageManager)
            val appName = packageManager.getApplicationLabel(info.applicationInfo).toString()
            mItemData.add(AppItemData(icon, appName, packageName, versionName))
        }
    }

    private fun isSystemApp(info: ApplicationInfo): Boolean {
        val isSysApp = info.flags and ApplicationInfo.FLAG_SYSTEM == ApplicationInfo.FLAG_SYSTEM
        val isSysUpd =
            info.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP == ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
        return isSysApp || isSysUpd
    }

    private fun loadSensorList(deviceInfo: JSONObject) {
        val deviceInfoProvider = DeviceInfoProvider(deviceInfo)
        val sensorInfo = deviceInfoProvider.getSensorInfo().sensorInfo
        if (TextUtils.isEmpty(sensorInfo)) {
            return
        }
        var sensorArray: JSONArray? = null
        try {
            sensorArray = JSONArray(sensorInfo)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (sensorArray == null) {
            return
        }
        for (i in 0 until sensorArray.length()) {
            val sensor = sensorArray.optJSONObject(i)
            val type = sensor.optInt("type")
            val icon = ContextCompat.getDrawable(this, getIconByType(type))
            val name = "name: " + sensor.optString("name")
            val vendor = "vendor: " + sensor.optString("vendor")
            mItemData.add(AppItemData(icon, name, vendor, typeToString(type)))
        }
    }

    private fun getIconByType(sensorType: Int): Int {
        return when (sensorType) {
            1, 10, 35 -> R.drawable.ic_sensor_accelerometer
            2, 14 -> R.drawable.ic_sensor_magnetic_field
            3, 11, 15, 20, 27 -> R.drawable.ic_sensor_orientation
            4, 16 -> R.drawable.ic_sensor_gyroscope
            5 -> R.drawable.ic_sensor_light
            6 -> R.drawable.ic_sensor_pressure
            7, 13 -> R.drawable.ic_sensor_temperature
            8 -> R.drawable.ic_sensor_proximity
            9 -> R.drawable.ic_sensor_gravity
            12 -> R.drawable.ic_sensor_humidity
            17, 30 -> R.drawable.ic_sensor_motion
            18, 19 -> R.drawable.ic_sensor_step
            21, 31 -> R.drawable.ic_sensor_heartrate
            22, 26 -> R.drawable.ic_sensor_tilt
            23, 24, 25, 28, 29, 32, 33, 34 -> R.drawable.ic_sensor_all
            36 -> R.drawable.ic_sensor_hinge
            else -> R.drawable.ic_sensor_private
        }
    }

    private fun typeToString(sensorType: Int): String {
        return when (sensorType) {
            1, 10, 35 -> "accelerometer"
            2, 14 -> "magnetic_field"
            3, 11, 15, 20, 27 -> "orientation"
            4, 16 -> "gyroscope"
            5 -> "light"
            6 -> "pressure"
            7, 13 -> "temperature"
            8 -> "proximity"
            9 -> "gravity"
            12 -> "humidity"
            17, 30 -> "motion"
            18, 19 -> "step"
            21, 31 -> "heartrate"
            22, 26 -> "tilt"
            23, 24, 25, 28, 29, 32, 33, 34 -> "unknown"
            36 -> "hinge"
            else -> "private"
        }
    }
}