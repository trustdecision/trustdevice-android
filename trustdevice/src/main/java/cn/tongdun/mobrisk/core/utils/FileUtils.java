package cn.tongdun.mobrisk.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: file util
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class FileUtils {

    public static Map<String, String> readFileByKey(String fileName, List<String> keys, String sep) {
        Map<String, String> info = new HashMap<>();
        BufferedReader br = null;
        if ((fileName != null) && (keys != null) && (new File(fileName).exists())) {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
                String raw;
                while ((raw = br.readLine()) != null) {
                    String paramValue = "";
                    if (!StringUtils.isEmpty(sep)) {
                        List<String> params;
                        String paramName;
                        params = StringUtils.splitNonRegex(raw, sep);
                        if (!params.isEmpty() && (paramName = params.get(0).trim()).length() != 0) {
                            if (keys.contains(paramName)) {
                                if (params.size() > 1) {
                                    paramValue = params.get(1).trim();
                                    info.put(paramName, paramValue);
                                }
                            }
                        }
                    } else {
                        for (String k : keys) {
                            if (raw.contains(k)) {
                                info.put(k, raw);
                            }
                        }
                    }
                }
                br.close();
            } catch (Exception ignored) {
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
        return info;
    }
}
