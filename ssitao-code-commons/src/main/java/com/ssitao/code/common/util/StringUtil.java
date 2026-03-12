package com.ssitao.code.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author ssitao
 * @since 1.0.0
 */
public class StringUtil {

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true:为空, false:不为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return true:不为空, false:为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白（包括null、空串、纯空格）
     *
     * @param str 字符串
     * @return true:为空白, false:不为空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空白
     *
     * @param str 字符串
     * @return true:不为空白, false:为空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str 字符串
     * @return true:是数字, false:不是数字
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断字符串是否为整数
     *
     * @param str 字符串
     * @return true:是整数, false:不是整数
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 字符串转Unicode
     *
     * @param str 字符串
     * @return Unicode字符串
     */
    public static String enUnicode(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c > 127) {
                sb.append("\\u").append(Integer.toHexString(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Unicode转字符串
     *
     * @param str Unicode字符串
     * @return 字符串
     */
    public static String deUnicode(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, Character.toString((char) Integer.parseInt(matcher.group(1), 16)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 转换字符串为十六进制编码
     *
     * @param s   字符串
     * @param mark 是否添加标记
     * @return 十六进制编码
     */
    public static String enHex(String s, Boolean mark) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        if (mark) {
            sb.append("0x");
        }
        for (byte b : s.getBytes(StandardCharsets.UTF_8)) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * 转换十六进制编码为字符串
     *
     * @param s 十六进制编码
     * @return 字符串
     */
    public static String deHex(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        if (s.startsWith("0x") || s.startsWith("0X")) {
            s = s.substring(2);
        }
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 模板解析，替换占位符
     *
     * @param value 模板字符串
     * @param map   参数映射
     * @return 解析后的字符串
     */
    public static String parseKeyWord(String value, Map<String, Object> map) {
        if (isEmpty(value) || map == null || map.isEmpty()) {
            return value;
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String key = matcher.group(1);
            Object val = map.get(key);
            matcher.appendReplacement(sb, val != null ? val.toString() : "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 格式化字符串（类似MessageFormat）
     *
     * @param pattern 模板
     * @param args    参数
     * @return 格式化后的字符串
     */
    public static String format(String pattern, Object... args) {
        if (isEmpty(pattern)) {
            return EMPTY;
        }
        if (args == null || args.length == 0) {
            return pattern;
        }
        return MessageFormat.format(pattern, args);
    }

    /**
     * URL编码
     *
     * @param str 字符串
     * @return 编码后的字符串
     */
    public static String encodeUrl(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * URL解码
     *
     * @param str 字符串
     * @return 解码后的字符串
     */
    public static String decodeUrl(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 连接字符串数组
     *
     * @param strs     字符串数组
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(String[] strs, String separator) {
        if (strs == null || strs.length == 0) {
            return EMPTY;
        }
        if (strs.length == 1) {
            return strs[0] != null ? strs[0] : EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            if (strs[i] != null) {
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 连接字符串集合
     *
     * @param strs     字符串集合
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(Collection<String> strs, String separator) {
        if (strs == null || strs.isEmpty()) {
            return EMPTY;
        }
        return join(strs.toArray(new String[0]), separator);
    }

    /**
     * 字符串截取（超出长度添加省略号）
     *
     * @param str    字符串
     * @param length 最大长度
     * @return 截取后的字符串
     */
    public static String truncate(String str, int length) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length) + "...";
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return 首字母小写后的字符串
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 下划线转驼峰
     *
     * @param str 下划线字符串
     * @return 驼峰字符串
     */
    public static String toCamelCase(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                upperCase = true;
            } else {
                if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String toSnakeCase(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append('_');
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return 扩展名
     */
    public static String getExtension(String filename) {
        if (isEmpty(filename)) {
            return EMPTY;
        }
        int index = filename.lastIndexOf('.');
        if (index > 0 && index < filename.length() - 1) {
            return filename.substring(index + 1);
        }
        return EMPTY;
    }

    /**
     * 移除文件扩展名
     *
     * @param filename 文件名
     * @return 不含扩展名的文件名
     */
    public static String removeExtension(String filename) {
        if (isEmpty(filename)) {
            return EMPTY;
        }
        int index = filename.lastIndexOf('.');
        if (index > 0) {
            return filename.substring(0, index);
        }
        return filename;
    }

    /**
     * 判断字符串是否包含任意一个关键字
     *
     * @param str       字符串
     * @param keywords 关键字数组
     * @return true:包含, false:不包含
     */
    public static boolean containsAny(String str, String... keywords) {
        if (isEmpty(str)) {
            return false;
        }
        if (keywords == null || keywords.length == 0) {
            return false;
        }
        for (String keyword : keywords) {
            if (str.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个字符串是否相等（null安全）
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return true:相等, false:不相等
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }

    /**
     * 比较两个字符串是否相等（忽略大小写）
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return true:相等, false:不相等
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 字符串null转空字符串
     *
     * @param str 字符串
     * @return 非null字符串
     */
    public static String nullToEmpty(String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * 字符串空转null
     *
     * @param str 字符串
     * @return 可能为null的字符串
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    /**
     * 字符串重复
     *
     * @param str       字符串
     * @param repeatCnt 重复次数
     * @return 重复后的字符串
     */
    public static String repeat(String str, int repeatCnt) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (repeatCnt <= 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(str.length() * repeatCnt);
        for (int i = 0; i < repeatCnt; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 计算字符串中子串出现的次数
     *
     * @param str 字符串
     * @param sub 子串
     * @return 出现次数
     */
    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    // 私有构造函数，防止实例化
    private StringUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
