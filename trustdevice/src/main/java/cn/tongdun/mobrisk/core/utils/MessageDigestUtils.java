package cn.tongdun.mobrisk.core.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @description: Message Digest utils
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class MessageDigestUtils {

    public static String hash(String algorithm, byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);
            return StringUtils.byteToHexString(md.digest());
        } catch (Throwable ignored) {
        }
        return "";
    }

    public static String hash(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes(StandardCharsets.UTF_8));
            return StringUtils.byteToHexString(md.digest());
        } catch (Throwable ignored) {
        }
        return "";
    }
}
