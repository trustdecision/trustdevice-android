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
    private String screenResolution;
   private String screenInches;
   private String screenBrightness;


    public BuildInfoRawData(String model, String brand, String manufacturer, String androidVersion, String sdkVersion, String kernelVersion, String fingerprint, String host, String screenResolution, String screenInches, String screenBrightness, String screenOffTimeout) {
        this.model = model;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.androidVersion = androidVersion;
        this.sdkVersion = sdkVersion;
        this.kernelVersion = kernelVersion;
        this.fingerprint = fingerprint;
        this.host = host;
        this.screenResolution = screenResolution;
        this.screenInches = screenInches;
        this.screenBrightness = screenBrightness;
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

    public String getScreenResolution() {
        return screenResolution;
    }

    public String getScreenInches() {
        return screenInches;
    }

    public String getScreenBrightness() {
        return screenBrightness;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("brand", getBrand()));
        data.add(new Pair<>("model", getModel()));
        data.add(new Pair<>("manufacturer", getManufacturer()));
        data.add(new Pair<>("android version", getAndroidVersion()));
        data.add(new Pair<>("sdk version", getSdkVersion()));
        data.add(new Pair<>("kernel version", getKernelVersion()));
        data.add(new Pair<>("fingerprint", getFingerprint()));
        data.add(new Pair<>("host", getHost()));
        data.add(new Pair<>("screen resolution", getScreenResolution()));
        data.add(new Pair<>("screen inches", getScreenInches()));
        data.add(new Pair<>("screen brightness", getScreenBrightness()));
        return data;
    }
}
