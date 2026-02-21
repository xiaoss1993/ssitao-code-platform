
package com.ssitao.code.frame.mybatisflex.core.audit.http;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hash 工具类。
 */
public class HashUtil {

    private HashUtil() {
    }

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    public static String md5(String srcStr) {
        return hash("MD5", srcStr);
    }

    public static String sha256(String srcStr) {
        return hash("SHA-256", srcStr);
    }

    public static String hash(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            ret.append(HEX_DIGITS[(b >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[b & 0x0f]);
        }
        return ret.toString();
    }

}
