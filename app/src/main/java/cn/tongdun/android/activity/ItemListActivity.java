package cn.tongdun.android.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trustdevice.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.tongdun.android.adapter.AppListRecyclerViewAdapter;
import cn.tongdun.android.base.BaseActivity;
import cn.tongdun.android.beans.AppItemData;
import cn.tongdun.mobrisk.TDRisk;
import cn.tongdun.mobrisk.providers.sensor.SensorInfoProvider;

public class ItemListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_item_list)
    RecyclerView rvItemList;

    private int mType;
    private boolean mShowSystemApp = false;
    private List<AppItemData> mItemData = new ArrayList<>();
    private AppListRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_app_list;
    }

    @Override
    protected void initData() {
        mType = getIntent().getIntExtra("type", -1);
        JSONObject deviceInfo = TDRisk.getBlackbox();
        if (mType == 0) {
            loadInstalledAppList(mShowSystemApp);
        } else if (mType == 1) {
            loadSensorList(deviceInfo);
        }
    }

    @Override
    protected void initView() {
        if (mType == 0) {
            tvTitle.setText(getResources().getString(R.string.app_list));
            tvTitleRight.setText(getResources().getString(R.string.show_system_apps));
        } else if (mType == 1) {
            tvTitle.setText(getResources().getString(R.string.sensor_list));
            tvTitleRight.setVisibility(View.GONE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvItemList.setLayoutManager(manager);
        mAdapter = new AppListRecyclerViewAdapter(mItemData);
        rvItemList.setAdapter(mAdapter);
        rvItemList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @OnClick({R.id.tv_title, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                this.finish();
                break;
            case R.id.tv_title_right:
                mShowSystemApp = !mShowSystemApp;
                if (mShowSystemApp) {
                    tvTitleRight.setText(getResources().getString(R.string.hide_system_apps));
                } else {
                    tvTitleRight.setText(getResources().getString(R.string.show_system_apps));
                }
                loadInstalledAppList(mShowSystemApp);
                mAdapter.updateData(mItemData);
                break;
        }
    }

    private void loadInstalledAppList(boolean showSystemApp) {
        PackageManager packageManager = getPackageManager();
        if (packageManager == null) {
            return;
        }
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfoList) {
            if (info == null) {
                continue;
            }
            if (!showSystemApp && isSystemApp(info.applicationInfo)) {
                continue;
            }
            String packageName = info.packageName;
            String versionName = info.versionName;
            Drawable icon = info.applicationInfo.loadIcon(packageManager);
            String appName = packageManager.getApplicationLabel(info.applicationInfo).toString();
//            if (mAppItemDataSet.contains(appName)){
//                continue;
//            }
            mItemData.add(new AppItemData(icon, appName, packageName, versionName));
        }
    }

    private boolean isSystemApp(ApplicationInfo info) {
        boolean isSysApp = (info.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM;
        boolean isSysUpd = (info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return isSysApp || isSysUpd;
    }

    private void loadSensorList(JSONObject deviceInfo) {

        SensorInfoProvider sensorInfoProvider = new SensorInfoProvider(deviceInfo);
        String sensorInfo = sensorInfoProvider.getSensorInfo();
        if (TextUtils.isEmpty(sensorInfo)) {
            return;
        }
        JSONArray sensorArray = null;
        try {
            sensorArray = new JSONArray(sensorInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sensorArray == null) {
            return;
        }
        for (int i = 0; i < sensorArray.length(); i++) {
            JSONObject sensor = sensorArray.optJSONObject(i);
            int type = sensor.optInt("type");
            Drawable icon = ContextCompat.getDrawable(this, getIconByType(type));
            String name = "name: " + sensor.optString("name");
            String vendor = "vendor: " + sensor.optString("vendor");
            mItemData.add(new AppItemData(icon, name, vendor, typeToString(type)));
        }
    }

    private int getIconByType(int sensorType) {
        switch (sensorType) {
            case 1:
            case 10:
            case 35:
                return R.drawable.ic_sensor_accelerometer;
            case 2:
            case 14:
                return R.drawable.ic_sensor_magnetic_field;
            case 3:
            case 11:
            case 15:
            case 20:
            case 27:
                return R.drawable.ic_sensor_orientation;
            case 4:
            case 16:
                return R.drawable.ic_sensor_gyroscope;
            case 5:
                return R.drawable.ic_sensor_light;
            case 6:
                return R.drawable.ic_sensor_pressure;
            case 7:
            case 13:
                return R.drawable.ic_sensor_temperature;
            case 8:
                return R.drawable.ic_sensor_proximity;
            case 9:
                return R.drawable.ic_sensor_gravity;
            case 12:
                return R.drawable.ic_sensor_humidity;
            case 17:
            case 30:
                return R.drawable.ic_sensor_motion;
            case 18:
            case 19:
                return R.drawable.ic_sensor_step;
            case 21:
            case 31:
                return R.drawable.ic_sensor_heartrate;
            case 22:
            case 26:
                return R.drawable.ic_sensor_tilt;
            case 23:
            case 24:
            case 25:
            case 28:
            case 29:
            case 32:
            case 33:
            case 34:
                return R.drawable.ic_sensor_all;
            case 36:
                return R.drawable.ic_sensor_hinge;
            default:
                return R.drawable.ic_sensor_private;
        }
    }

    private String typeToString(int sensorType) {
        switch (sensorType) {
            case 1:
            case 10:
            case 35:
                return "accelerometer";
            case 2:
            case 14:
                return "magnetic_field";
            case 3:
            case 11:
            case 15:
            case 20:
            case 27:
                return "orientation";
            case 4:
            case 16:
                return "gyroscope";
            case 5:
                return "light";
            case 6:
                return "pressure";
            case 7:
            case 13:
                return "temperature";
            case 8:
                return "proximity";
            case 9:
                return "gravity";
            case 12:
                return "humidity";
            case 17:
            case 30:
                return "motion";
            case 18:
            case 19:
                return "step";
            case 21:
            case 31:
                return "heartrate";
            case 22:
            case 26:
                return "tilt";
            case 23:
            case 24:
            case 25:
            case 28:
            case 29:
            case 32:
            case 33:
            case 34:
                return "unknown";
            case 36:
                return "hinge";
            default:
                return "private";
        }
    }
}