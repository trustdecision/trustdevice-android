package cn.tongdun.mobrisk.core.collectors

import android.content.Context
import android.os.Build

interface DeviceInfoTamperedInterface {
    fun tampered(context: Context?): Boolean
}

class DeviceInfoTamperedCollector : DeviceInfoTamperedInterface {
    override fun tampered(context: Context?): Boolean  {
        return !Build.FINGERPRINT.contains(Build.BRAND,true)
    }
}