package cn.tongdun.android.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import cn.tongdun.mobrisk.TDRisk.getBlackbox
import cn.tongdun.mobrisk.providers.DeviceInfoProvider
import com.trustdevice.android.R
import org.json.JSONException


class DeviceIdFragment : Fragment() {

    @BindView(R.id.tv_device_id)
    lateinit var mTvDeviceID: TextView

    @BindView(R.id.tv_android_id)
    lateinit var mTvAndroidID: TextView

    @BindView(R.id.tv_gsf_id)
    lateinit var mTvGSF_ID: TextView

    @BindView(R.id.tv_gad_id)
    lateinit var mTvGAD_ID: TextView

    @BindView(R.id.tv_media_drm_id)
    lateinit var mTvMediaDrmID: TextView

    @BindView(R.id.tv_vmd_id)
    lateinit var mTvVMD_ID: TextView

    @BindView(R.id.tv_risk_labels_title)
    lateinit var mTvRiskLabelsTitle: TextView

    @BindView(R.id.tv_risk_labels)
    lateinit var mTvRiskLabels: TextView
    private var mDeviceInfoProvider: DeviceInfoProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDeviceID()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_device_id, container, false)
        ButterKnife.bind(this, rootView)
        initView()
        return rootView
    }

    private fun getDeviceID() {
        val deviceInfo = getBlackbox()
        try {
            Log.d("TrustDevice", "deviceInfo = " + deviceInfo.toString(1))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mDeviceInfoProvider = DeviceInfoProvider(deviceInfo)
    }

    private fun initView() {
        mTvDeviceID.text = mDeviceInfoProvider!!.getDeviceInfo().deviceId
        mTvAndroidID.text = mDeviceInfoProvider!!.getDeviceInfo().androidId
        mTvGSF_ID.text = mDeviceInfoProvider!!.getDeviceInfo().gsfId
        mTvGAD_ID.text = mDeviceInfoProvider!!.getDeviceInfo().gadId
        mTvMediaDrmID.text = mDeviceInfoProvider!!.getDeviceInfo().mediaDrmId
        mTvVMD_ID.text = mDeviceInfoProvider!!.getDeviceInfo().vbMetaDigest
        val riskLabels = mDeviceInfoProvider!!.getRiskInfo().toString()
        if (TextUtils.isEmpty(riskLabels)) {
            mTvRiskLabelsTitle.visibility = View.GONE
            mTvRiskLabels.visibility = View.GONE
        } else {
            mTvRiskLabelsTitle.visibility = View.VISIBLE
            mTvRiskLabels.visibility = View.VISIBLE
            mTvRiskLabels.text = riskLabels
        }
    }
}