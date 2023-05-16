package cn.tongdun.android

import android.app.Application
import cn.tongdun.mobrisk.TDRisk.init
import com.tencent.mmkv.MMKV

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        val isAgree = MMKV.defaultMMKV().decodeBool("isAgreePrivacyAgreement")
        if (isAgree) {
            init(this)
        }
    }
}