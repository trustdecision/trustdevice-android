package cn.tongdun.android.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trustdevice.android.R;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tongdun.android.activity.ItemListActivity;
import cn.tongdun.android.adapter.DeviceInfoItemAdapter;
import cn.tongdun.mobrisk.TDRisk;
import cn.tongdun.mobrisk.providers.applist.AppListProvider;
import cn.tongdun.mobrisk.providers.battery.BatteryInfoProvider;
import cn.tongdun.mobrisk.providers.build.BuildInfoProvider;
import cn.tongdun.mobrisk.providers.memory.MemoryInfoProvider;
import cn.tongdun.mobrisk.providers.sensor.SensorInfoProvider;
import cn.tongdun.mobrisk.providers.setting.SettingInfoProvider;

/**
 * @description:DeviceId Fragment
 * @author: wuzuchang
 * @date: 2023/2/28
 */
@SuppressLint("SetTextI18n")
public class DeviceInfoFragment extends Fragment {

    //BuildInfo
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_model)
    TextView mTvModel;
    @BindView(R.id.rv_build_info)
    RecyclerView mRcBuildInfo;
    private BuildInfoProvider mBuildInfoProvider;
    //Setting
    @BindView(R.id.rv_setting)
    RecyclerView mRcSetting;
    private SettingInfoProvider mSettingInfoProvider;
    // MemoryIndo
    @BindView(R.id.tv_internal_storage_path)
    TextView mTvInternalStoragePath;
    @BindView(R.id.internal_storage_progress)
    ProgressBar mPbInternalStorageProgress;
    @BindView(R.id.tv_internal_storage_scale)
    TextView mTvInternalStorageScale;
    @BindView(R.id.tv_internal_storage_info)
    TextView mTvInternalStorageInfo;
    @BindView(R.id.tv_ram_path)
    TextView mTvRAMPath;
    @BindView(R.id.ram_progress)
    ProgressBar mPbRAMProgress;
    @BindView(R.id.tv_ram_scale)
    TextView mTvRAMScale;
    @BindView(R.id.tv_ram_info)
    TextView mTvRAMInfo;
    private MemoryInfoProvider mMemoryInfoProvider;
    // Battery
    @BindView(R.id.tv_battery_status)
    TextView mTvBatterStatus;
    @BindView(R.id.tv_battery_health)
    TextView mTvBatterHealth;
    @BindView(R.id.battery_progress)
    ProgressBar mPbBatteryProgress;
    @BindView(R.id.tv_battery_power)
    TextView mTvBatteryPower;
    @BindView(R.id.tv_battery_info)
    TextView mTvBatteryInfo;
    private BatteryInfoProvider mBatteryInfoProvider;
    // Apps
    @BindView(R.id.tv_app_total)
    TextView mTvAppTotal;
    @BindView(R.id.all_apps_layout)
    ConstraintLayout mAppLayout;
    private AppListProvider mAppListProvider;
    //Sensor
    @BindView(R.id.tv_sensor_total)
    TextView mTvSensorTotal;
    @BindView(R.id.all_sensor_layout)
    ConstraintLayout mSensorLayout;
    private SensorInfoProvider mSensorInfoProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeviceInfo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_info, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void getDeviceInfo() {
        JSONObject deviceInfo = TDRisk.getBlackbox();
        mBuildInfoProvider = new BuildInfoProvider(deviceInfo);
        mSettingInfoProvider = new SettingInfoProvider(deviceInfo);
        mMemoryInfoProvider = new MemoryInfoProvider(deviceInfo);
        mBatteryInfoProvider = new BatteryInfoProvider(deviceInfo);
        mAppListProvider = new AppListProvider(deviceInfo);
        mSensorInfoProvider = new SensorInfoProvider(deviceInfo);
    }

    private void initView() {
        initBuildInfoView();
        initSettingView();
        initInternalStorageView();
        initRAMView();
        initBatteryView();
        initAppTotalView();
        initSensorTotalView();
    }

    private void initBuildInfoView() {
        mTvBrand.setText(mBuildInfoProvider.getBrand());
        mTvModel.setText(mBuildInfoProvider.getModel());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRcBuildInfo.setLayoutManager(manager);
        List<Pair<String, String>> buildData = mBuildInfoProvider.getRawData();
        DeviceInfoItemAdapter deviceInfoItemAdapter = new DeviceInfoItemAdapter(buildData);
        mRcBuildInfo.setAdapter(deviceInfoItemAdapter);
    }

    private void initSettingView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRcSetting.setLayoutManager(manager);
        List<Pair<String, String>> buildData = mSettingInfoProvider.getRawData();
        DeviceInfoItemAdapter deviceInfoItemAdapter = new DeviceInfoItemAdapter(buildData);
        mRcSetting.setAdapter(deviceInfoItemAdapter);
    }

    private void initInternalStorageView() {
        mTvInternalStoragePath.setText(Environment.getDataDirectory().getPath());
        double totalStorage = mMemoryInfoProvider.getTotalStorage() / Math.pow(1024, 3);
        double availableStorage = mMemoryInfoProvider.getAvailableStorage() / Math.pow(1024, 3);
        double useStorage = totalStorage - availableStorage;
        String internalStorageInfo = String.format(Locale.US, "%.2fGB", useStorage) + " / " + String.format(Locale.US, "%.2fGB", totalStorage) + ", Free: " + String.format(Locale.US, "%.2fGB", availableStorage);
        mTvInternalStorageInfo.setText(internalStorageInfo);
        double scale = 100 / totalStorage;
        mPbInternalStorageProgress.setProgress((int) (useStorage * scale));
        mTvInternalStorageScale.setText(String.format(Locale.US, "%.2f", useStorage / totalStorage * 100) + "%");
    }

    private void initRAMView() {
        mTvRAMPath.setText("The state at the time of collection");
        double totalMemory = mMemoryInfoProvider.getTotalMemory() / Math.pow(1024, 3);
        double availableMemory = mMemoryInfoProvider.getAvailableMemory() / Math.pow(1024, 3);
        double useMemory = totalMemory - availableMemory;
        String internalStorageInfo = String.format(Locale.US, "%.2fGB", useMemory) + " / " + String.format(Locale.US, "%.2fGB", totalMemory) + ", Free: " + String.format(Locale.US, "%.2fGB", availableMemory);
        mTvRAMInfo.setText(internalStorageInfo);
        double scale = 100 / totalMemory;
        mPbRAMProgress.setProgress((int) (useMemory * scale));
        mTvRAMScale.setText(String.format(Locale.US, "%.2f", useMemory / totalMemory * 100) + "%");
    }

    private void initBatteryView() {
        mTvBatterStatus.setText("(" + mBatteryInfoProvider.getStatus() + ")");
        mTvBatterHealth.setText("total capacity: " + mBatteryInfoProvider.getTotalCapacity() + "mAh");
        int power = mBatteryInfoProvider.getLevel();
        mPbBatteryProgress.setProgress(power);
        mTvBatteryPower.setText(power + "%");
        int temp = mBatteryInfoProvider.getTemp();
        double temperature = BigDecimal.valueOf((float) temp / 10).setScale(2, RoundingMode.HALF_UP).doubleValue();
        mTvBatteryInfo.setText("health: " + mBatteryInfoProvider.getHealth() + ", temperature: " + temperature + "℃");
    }

    private void initAppTotalView() {
        int total = mAppListProvider.getUserAppTotal() + mAppListProvider.getSystemAppTotal();
        mTvAppTotal.setText(total + "");
    }

    private void initSensorTotalView() {
        int total = mSensorInfoProvider.getSensorTotal();
        mTvSensorTotal.setText(total + "");
    }

    @OnClick({R.id.all_apps_layout, R.id.all_sensor_layout})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.all_apps_layout:
                jumpToListActivity(0);
                break;
            case R.id.all_sensor_layout:
                jumpToListActivity(1);
                break;
        }
    }

    private void jumpToListActivity(int type) {
        Intent sensor = new Intent(getActivity(), ItemListActivity.class);
        sensor.putExtra("type", type);
        startActivity(sensor);
    }

}
