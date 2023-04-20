package cn.tongdun.mobrisk.core.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;

import cn.tongdun.mobrisk.beans.DeviceInfo;

/**
 * @description: format device info
 * @author: wuzuchang
 * @date: 2022/12/7
 */
public class DeviceInfoUtils {

    public static JSONObject format(String deviceId, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return new JSONObject();
        }
        JSONObject result = new JSONObject();
        JSONObject detail = new JSONObject();
        Field[] fields = deviceInfo.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String key = field.getName();
                Object object = field.get(deviceInfo);
                detail.put(key, String.valueOf(object));
            } catch (Exception ignored) {
            }
        }
        try {
            result.put(Constants.KEY_DEVICE_ID, deviceId);
            result.put(Constants.KEY_DEVICE_RISK_LABEL, deviceRisk(detail));
            result.put(Constants.KEY_DEVICE_DETAIL, detail);
        } catch (JSONException ignored) {
        }
        return result;
    }

    private static JSONObject deviceRisk(JSONObject data) {
        JSONObject risk = new JSONObject();
        if (data == null) {
            return risk;
        }
        try {
            risk.put(Constants.KEY_ROOT, data.opt(Constants.KEY_ROOT));
            risk.put(Constants.KEY_DEBUG, data.opt(Constants.KEY_DEBUG));
            risk.put(Constants.KEY_MULTIPLE, getMultiple(data));
            risk.put(Constants.KEY_XPOSED, data.opt(Constants.KEY_XPOSED));
            risk.put(Constants.KEY_MAGISK, data.opt(Constants.KEY_MAGISK));
            risk.put(Constants.KEY_HOOK, data.opt(Constants.KEY_HOOK));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return risk;
    }

    private static String getMultiple(JSONObject data) {

        if (data == null) {
            return "false";
        }
        String filePath = data.optString(Constants.KEY_FILES_ABSOLUTE_PATH);
        String packageName = data.optString(Constants.KEY_PACKAGE_NAME);
        String[] filePaths = filePath.split(packageName);
        if (filePaths.length <= 0) {
            return "false";
        }
        String dir = filePaths[0];
        int riskLength = 3;
        if (dir.startsWith(File.separator)) {
            riskLength = 4;
        }
        String[] dirs = dir.split(File.separator);
        if (dirs.length > riskLength) {
            return "true";
        }
        if (!"0".equals(dirs[dirs.length - 1])) {
            return "true";
        }
        return "false";
    }
}
