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
        String path = envMap.get("PATH");
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String[] paths = path.split(":");
        for (String suDir : paths) {
            String suPath = suDir + File.separator + fileName;
            File file = new File(suPath);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
}
