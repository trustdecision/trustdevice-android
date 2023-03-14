package cn.tongdun.mobrisk.core.utils;

import android.text.TextUtils;

import java.io.File;
import java.util.Map;

/**
 * @author collam
 * @date 2023/3/14
 */
public class EnvUtils {
    public static boolean fileInEnv(String fileName){
        Map<String, String> envMap = System.getenv();
        String pathValue = envMap.get("PATH");
        if (TextUtils.isEmpty(pathValue)) {
            return false;
        }
        String[] paths = pathValue.split(":");
        for (String path : paths) {
            String fullPath = path + File.separator + fileName;
            File file = new File(fullPath);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
}
