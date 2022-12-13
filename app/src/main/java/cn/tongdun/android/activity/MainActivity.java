package cn.tongdun.android.activity;

import android.text.TextUtils;
import android.util.Pair;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trustdevice.android.R;
import cn.tongdun.android.adapter.DeviceModuleItemAdapter;
import cn.tongdun.android.base.BaseActivity;
import cn.tongdun.android.beans.DetailsItemBean;
import cn.tongdun.android.beans.DeviceModuleItemBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.tongdun.mobrisk.TDRisk;
import cn.tongdun.mobrisk.TDRiskCallback;
import cn.tongdun.mobrisk.providers.InfoProvider;
import cn.tongdun.mobrisk.providers.applist.AppListProvider;
import cn.tongdun.mobrisk.providers.battery.BatteryInfoProvider;
import cn.tongdun.mobrisk.providers.build.BuildInfoProvider;
import cn.tongdun.mobrisk.providers.cpu.CpuInfoProvider;
import cn.tongdun.mobrisk.providers.device_id.DeviceIdProvider;
import cn.tongdun.mobrisk.providers.memory.MemoryInfoProvider;
import cn.tongdun.mobrisk.providers.risk.RiskInfoProvider;
import cn.tongdun.mobrisk.providers.screen.ScreenInfoProvider;
import cn.tongdun.mobrisk.providers.setting.SettingInfoProvider;
import cn.tongdun.mobrisk.providers.sensor.SensorInfoProvider;

public class MainActivity extends BaseActivity {

    @BindView(R.id.device_info_container)
    RecyclerView rvDeviceInfoContainer;
    private DeviceModuleItemAdapter mAdapter;
    private List<DeviceModuleItemBean> mItemData = new ArrayList<>();

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        createDeviceInfo();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rvDeviceInfoContainer.setLayoutManager(manager);
        mAdapter = new DeviceModuleItemAdapter(mItemData);
        rvDeviceInfoContainer.setAdapter(mAdapter);
        TDRisk.Builder builder = new TDRisk.Builder();
        builder.callback(new TDRiskCallback() {
            @Override
            public void onEvent(JSONObject deviceInfo) {
                String deviceID = deviceInfo.optString("device_id");
                JSONObject deviceRisk = deviceInfo.optJSONObject("device_risk_label");
                JSONObject deviceDetail = deviceInfo.optJSONObject("device_detail");
            }
        });
        TDRisk.initWithOptions(this, builder);
    }

    private void createDeviceInfo() {
        JSONObject deviceInfo = TDRisk.getBlackbox();
        createData(deviceInfo);
    }

    private void createData(JSONObject deviceInfo) {
        createItemInfo(new DeviceIdProvider(deviceInfo));
        createItemInfo(new RiskInfoProvider(deviceInfo));
        createItemInfo(new BuildInfoProvider(deviceInfo));
        createItemInfo(new ScreenInfoProvider(deviceInfo));
        createItemInfo(new BatteryInfoProvider(deviceInfo));
        createItemInfo(new SettingInfoProvider(deviceInfo));
        createItemInfo(new CpuInfoProvider(deviceInfo));
        createItemInfo(new MemoryInfoProvider(deviceInfo));
        createItemInfo(new SensorInfoProvider(deviceInfo));
        createAppList(new AppListProvider(deviceInfo));
    }

    private void createItemInfo(InfoProvider<String> infoProvider) {
        List<DetailsItemBean> detailsItemBeanList = new ArrayList<>();
        for (Pair<String, String> infos : infoProvider.getRawData()) {
            detailsItemBeanList.add(new DetailsItemBean(infos.first, infos.second));
        }
        mItemData.add(new DeviceModuleItemBean(infoProvider.getProviderName(), detailsItemBeanList));
    }

    private void createAppList(InfoProvider<String[]> appListProvider) {
        for (Pair<String, String[]> infos : appListProvider.getRawData()) {
            List<DetailsItemBean> appListDetailsItemList = new ArrayList<>();
            for (String appInfo : infos.second) {
                if (TextUtils.isEmpty(appInfo)) {
                    continue;
                }
                appListDetailsItemList.add(new DetailsItemBean(appInfo, ""));
            }
            mItemData.add(new DeviceModuleItemBean(infos.first, appListDetailsItemList));
        }
    }
}