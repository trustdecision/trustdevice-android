package cn.tongdun.mobrisk.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: string util
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return ((str == null) || ("".equals(str.trim()) || str.length() == 0));
    }

    public static List<String> splitNonRegex(String input, String delim) {
        List<String> list = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return list;
        }
        if (delim == null || delim.length() == 0) {
            list.add(input);
            return list;
        }
        if(input.equals(delim)){
            return list;
        }
        while (true) {
            int index;
            index = input.indexOf(delim);
            if (index == -1) {
                if (!isEmpty(input)) {
                    list.add(input);
                }
                return list;
            }
            if (index != 0) {
                list.add(input.substring(0, index));
            }
            input = input.substring(index + delim.length());
        }
    }

    public static String byteToHexString(byte[] data){
        StringBuilder hex = new StringBuilder();
        // Iterating through each byte in the array
        for (byte i : data) {
            hex.append(String.format("%02X", i));
        }
        return hex.toString();
    }
}
