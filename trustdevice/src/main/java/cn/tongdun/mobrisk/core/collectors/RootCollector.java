package cn.tongdun.mobrisk.core.collectors;

import android.text.TextUtils;

import java.io.File;
import java.util.Map;

/**
 * @description: Root
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class RootCollector {

    public boolean getRoot() {
        Map<String, String> envMap = System.getenv();
        String path = envMap.get("PATH");
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String[] paths = path.split(":");
        for (String suDir : paths) {
            String suPath = suDir + File.separator + "su";
            File file = new File(suPath);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
}
