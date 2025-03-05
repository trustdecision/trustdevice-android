package cn.tongdun.android.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.tongdun.android.base.BaseFragment
import cn.tongdun.mobrisk.TDRisk.getBlackbox
import cn.tongdun.mobrisk.providers.DeviceInfoProvider
import com.trustdevice.android.demo.databinding.FragmentDeviceIdBinding
import org.json.JSONException


class DeviceIdFragment : BaseFragment<FragmentDeviceIdBinding>() {
    private var mDeviceInfoProvider: DeviceInfoProvider? = null


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeviceIdBinding = FragmentDeviceIdBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDeviceID()
        initView()
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
        binding.run {
            mDeviceInfoProvider?.run {
                tvDeviceId.text = getDeviceInfo().deviceId
                tvAndroidId.text = getDeviceInfo().androidId
                if(getDeviceInfo().gsfId.isNotEmpty()) {
                    tvGsfId.text = getDeviceInfo().gsfId.toLong().toString(16)
                }
                tvGadId.text = getDeviceInfo().gadId
                tvMediaDrmId.text = getDeviceInfo().mediaDrmId
                tvVmdId.text = getDeviceInfo().vbMetaDigest
                val riskLabels = getRiskInfo().toString()
                if (TextUtils.isEmpty(riskLabels)) {
                    tvRiskLabelsTitle.visibility = View.GONE
                    tvRiskLabels.visibility = View.GONE
                } else {
                    tvRiskLabelsTitle.visibility = View.VISIBLE
                    tvRiskLabels.visibility = View.VISIBLE
                    tvRiskLabels.text = riskLabels
                }
            }
        }
    }
}