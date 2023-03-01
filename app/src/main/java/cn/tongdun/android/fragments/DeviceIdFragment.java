package cn.tongdun.android.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trustdevice.android.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tongdun.mobrisk.TDRisk;
import cn.tongdun.mobrisk.providers.device_id.DeviceIdProvider;
import cn.tongdun.mobrisk.providers.risk.RiskInfoProvider;

/**
 * @description:DeviceId Fragment
 * @author: wuzuchang
 * @date: 2023/2/28
 */
public class DeviceIdFragment extends Fragment {

    @BindView(R.id.tv_device_id)
    TextView mTvDeviceID;
    @BindView(R.id.tv_android_id)
    TextView mTvAndroidID;
    @BindView(R.id.tv_gsf_id)
    TextView mTvGSF_ID;
    @BindView(R.id.tv_media_drm_id)
    TextView mTvMediaDrmID;
    @BindView(R.id.tv_vmd_id)
    TextView mTvVMD_ID;
    @BindView(R.id.tv_risk_labels_title)
    TextView mTvRiskLabelsTitle;
    @BindView(R.id.tv_risk_labels)
    TextView mTvRiskLabels;
    private DeviceIdProvider mDeviceIdProvider;
    private RiskInfoProvider mRiskInfoProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeviceID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_id, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void getDeviceID() {
        JSONObject deviceInfo = TDRisk.getBlackbox();
        try {
            Log.d("TrustDevice", "deviceInfo = " + deviceInfo.toString(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDeviceIdProvider = new DeviceIdProvider(deviceInfo);
        mRiskInfoProvider = new RiskInfoProvider(deviceInfo);
    }

    private void initView() {
        mTvDeviceID.setText(mDeviceIdProvider.getDeviceId());
        mTvAndroidID.setText(mDeviceIdProvider.getAndroidId());
        mTvGSF_ID.setText(mDeviceIdProvider.getGsfId());
        mTvMediaDrmID.setText(mDeviceIdProvider.getMediaDrmId());
        mTvVMD_ID.setText(mDeviceIdProvider.getVbMetaDigest());
        String riskLabels = mRiskInfoProvider.getRiskLabels();
        if (TextUtils.isEmpty(riskLabels)) {
            mTvRiskLabelsTitle.setVisibility(View.GONE);
            mTvRiskLabels.setVisibility(View.GONE);
        } else {
            mTvRiskLabelsTitle.setVisibility(View.VISIBLE);
            mTvRiskLabels.setVisibility(View.VISIBLE);
            mTvRiskLabels.setText(riskLabels);
        }
    }
}
