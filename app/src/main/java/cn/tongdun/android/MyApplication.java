package cn.tongdun.android;


import android.app.Application;

import cn.tongdun.mobrisk.TDRisk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TDRisk.init(this);
    }
}
