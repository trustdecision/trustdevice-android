package cn.tongdun.android;


import android.app.Application;

import com.tencent.mmkv.MMKV;

import cn.tongdun.mobrisk.TDRisk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MMKV.initialize(this);

        boolean isAgree = MMKV.defaultMMKV().decodeBool("isAgreePrivacyAgreement");
        if (isAgree){
            TDRisk.init(this);
        }
    }
}
