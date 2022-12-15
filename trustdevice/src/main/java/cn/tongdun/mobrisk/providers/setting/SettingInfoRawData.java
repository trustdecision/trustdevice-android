package cn.tongdun.mobrisk.providers.setting;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: SettingInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class SettingInfoRawData extends RawData<String> {
    private String adbEnable;
    private String developmentSettingEnabled;
    private String httpProxy;
    private String dataRoaming;
    private String allowMockLocation;
    private String accessibilityEnabled;
    private String defaultInputMethod;
    private String touchExplorationEnabled;

    public SettingInfoRawData(String adbEnable, String developmentSettingEnabled, String httpProxy, String dataRoaming, String allowMockLocation, String accessibilityEnabled, String defaultInputMethod, String touchExplorationEnabled) {
        this.adbEnable = adbEnable;
        this.developmentSettingEnabled = developmentSettingEnabled;
        this.httpProxy = httpProxy;
        this.dataRoaming = dataRoaming;
        this.allowMockLocation = allowMockLocation;
        this.accessibilityEnabled = accessibilityEnabled;
        this.defaultInputMethod = defaultInputMethod;
        this.touchExplorationEnabled = touchExplorationEnabled;
    }

    private String getAdbEnable() {
        return formatSwitch(adbEnable);
    }

    private String getDevelopmentSettingEnabled() {
        return formatSwitch(developmentSettingEnabled);
    }

    private String getHttpProxy() {
        return httpProxy;
    }

    private String getDataRoaming() {
        return formatSwitch(dataRoaming);
    }

    private String getAllowMockLocation() {
        return formatSwitch(allowMockLocation);
    }

    private String getAccessibilityEnabled() {
        return formatSwitch(accessibilityEnabled);
    }

    private String getDefaultInputMethod() {
        return defaultInputMethod;
    }

    private String getTouchExplorationEnabled() {
        return formatSwitch(touchExplorationEnabled);
    }

    private String formatSwitch(String data) {
        return "1".equals(data) ? "open" : "close";
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("adb", getAdbEnable()));
        data.add(new Pair<>("development setting", getDevelopmentSettingEnabled()));
        data.add(new Pair<>("data roaming", getDataRoaming()));
        data.add(new Pair<>("mock location", getAllowMockLocation()));
        data.add(new Pair<>("accessibility", getAccessibilityEnabled()));
        data.add(new Pair<>("touch exploration", getTouchExplorationEnabled()));
        data.add(new Pair<>("http proxy", getHttpProxy()));
        data.add(new Pair<>("default inputMethod", getDefaultInputMethod()));
        return data;
    }
}
