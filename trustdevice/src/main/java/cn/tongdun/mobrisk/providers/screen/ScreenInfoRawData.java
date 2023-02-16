package cn.tongdun.mobrisk.providers.screen;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: ScreenInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class ScreenInfoRawData extends RawData<String> {
    private String screenResolution;
    private String screenInches;
    private String screenBrightness;
    private String screenOffTimeout;

    public ScreenInfoRawData(String screenResolution, String screenInches, String screenBrightness, String screenOffTimeout) {
        this.screenResolution = screenResolution;
        this.screenInches = screenInches;
        this.screenBrightness = screenBrightness;
        this.screenOffTimeout = screenOffTimeout;
    }

    private String getScreenResolution() {
        return screenResolution;
    }

    private String getScreenInches() {
        return screenInches;
    }

    private String getScreenBrightness() {
        return screenBrightness;
    }

    private String getScreenOffTimeout() {
        return screenOffTimeout;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("screen resolution", getScreenResolution()));
        data.add(new Pair<>("screen inches", getScreenInches()));
        data.add(new Pair<>("screen brightness", getScreenBrightness()));
        data.add(new Pair<>("screen Off timeout", getScreenOffTimeout()));
        return data;
    }
}
