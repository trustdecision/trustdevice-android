package cn.tongdun.mobrisk.providers.build;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: BuildInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class BuildInfoRawData extends RawData<String> {
    private String model;
    private String brand;
    private String manufacturer;
    private String androidVersion;
    private String sdkVersion;
    private String kernelVersion;
    private String fingerprint;
    private String host;

    public BuildInfoRawData(String model,String brand, String manufacturer, String androidVersion, String sdkVersion, String kernelVersion, String fingerprint, String host) {
        this.model = model;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.androidVersion = androidVersion;
        this.sdkVersion = sdkVersion;
        this.kernelVersion = kernelVersion;
        this.fingerprint = fingerprint;
        this.host = host;
    }

    private String getModel() {
        return model;
    }

    private String getBrand() {
        return brand;
    }

    private String getManufacturer() {
        return manufacturer;
    }

    private String getAndroidVersion() {
        return androidVersion;
    }

    private String getSdkVersion() {
        return sdkVersion;
    }

    private String getKernelVersion() {
        return kernelVersion;
    }

    private String getFingerprint() {
        return fingerprint;
    }

    private String getHost() {
        return host;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("model", getModel()));
        data.add(new Pair<>("brand", getBrand()));
        data.add(new Pair<>("manufacturer", getManufacturer()));
        data.add(new Pair<>("android version", getAndroidVersion()));
        data.add(new Pair<>("sdk version", getSdkVersion()));
        data.add(new Pair<>("kernel version", getKernelVersion()));
        data.add(new Pair<>("fingerprint", getFingerprint()));
        data.add(new Pair<>("host", getHost()));
        return data;
    }
}
