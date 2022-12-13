package cn.tongdun.mobrisk.providers.risk;

import android.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: RiskInfoRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class RiskInfoRawData extends RawData<String> {
    private String root;
    private String debug;
    private String multiple;

    public RiskInfoRawData(String root, String debug, String multiple) {
        this.root = root;
        this.debug = debug;
        this.multiple = multiple;
    }

    private String getRoot() {
        return root;
    }

    private String getDebug() {
        return debug;
    }

    private String getMultiple() {

        return multiple;
    }

    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("root", getRoot()));
        data.add(new Pair<>("debug", getDebug()));
        data.add(new Pair<>("multiple", getMultiple()));
        return data;
    }
}
