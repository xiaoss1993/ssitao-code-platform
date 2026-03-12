package com.ssitao.code.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author ssitao
 * @since 1.0.0
 */
public class DateUtils {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 一天的毫秒数
     */
    public static final long DAY_MILLIS = 24 * 60 * 60 * 1000L;

    /**
     * 一小时的毫秒数
     */
    public static final long HOUR_MILLIS = 60 * 60 * 1000L;

    /**
     * 一分钟的毫秒数
     */
    public static final long MINUTE_MILLIS = 60 * 1000L;

    /**
     * 一秒钟的毫秒数
     */
    public static final long SECOND_MILLIS = 1000L;

    /**
     * 格式化日期为 yyyy-MM-dd
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化日期为 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatDateTime(Date date) {
        return format(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 格式化时间为 HH:mm:ss
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatTime(Date date) {
        return format(date, DEFAULT_TIME_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param pattern 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化 LocalDate
     *
     * @param date   LocalDate
     * @param pattern 格式
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date, String pattern) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化 LocalDateTime
     *
     * @param dateTime LocalDateTime
     * @param pattern 格式
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析字符串为日期
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date parseDate(String dateStr) {
        return parse(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 解析字符串为日期时间
     *
     * @param dateStr 日期时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String dateStr) {
        return parse(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 解析字符串为日期
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期
     */
    public static Date parse(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr + ", pattern: " + pattern, e);
        }
    }

    /**
     * 解析字符串为 LocalDate
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return LocalDate
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析字符串为 LocalDateTime
     *
     * @param dateStr 日期时间字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前日期字符串 yyyy-MM-dd
     *
     * @return 当前日期字符串
     */
    public static String today() {
        return formatDate(now());
    }

    /**
     * 获取当前日期时间字符串 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前日期时间字符串
     */
    public static String nowStr() {
        return formatDateTime(now());
    }

    /**
     * 日期加减天数
     *
     * @param date 日期
     * @param days 天数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 日期加月数
     *
     * @param date   日期
     * @param months 月数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 日期加年数
     *
     * @param date  日期
     * @param years 年数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 日期加小时数
     *
     * @param date  日期
     * @param hours 小时数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 日期加分钟数
     *
     * @param date    日期
     * @param minutes 分钟数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 日期加秒数
     *
     * @param date    日期
     * @param seconds 秒数（正数加，负数减）
     * @return 计算后的日期
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * 计算两个日期之间的天数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 天数差
     */
    public static long daysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / DAY_MILLIS;
    }

    /**
     * 计算两个日期之间的小时数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 小时数差
     */
    public static long hoursBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / HOUR_MILLIS;
    }

    /**
     * 计算两个日期之间的分钟数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 分钟数差
     */
    public static long minutesBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / MINUTE_MILLIS;
    }

    /**
     * 计算两个日期之间的秒数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 秒数差
     */
    public static long secondsBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / SECOND_MILLIS;
    }

    /**
     * 获取日期所在月份的第一天
     *
     * @param date 日期
     * @return 月份第一天
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取日期所在月份的最后一天
     *
     * @param date 日期
     * @return 月份最后一天
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取日期所在年份的第一天
     *
     * @param date 日期
     * @return 年份第一天
     */
    public static Date firstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }

    /**
     * 获取日期所在年份的最后一天
     *
     * @param date 日期
     * @return 年份最后一天
     */
    public static Date lastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    /**
     * 判断是否是同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一天, false:不同天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断日期是否在区间内
     *
     * @param date      日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return true:在区间内, false:不在区间内
     */
    public static boolean isBetween(Date date, Date startDate, Date endDate) {
        if (date == null) {
            return false;
        }
        if (startDate != null && date.before(startDate)) {
            return false;
        }
        return endDate == null || !date.after(endDate);
    }

    /**
     * 获取年龄
     *
     * @param birthDate 生日
     * @return 年龄
     */
    public static int getAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        cal.setTime(birthDate);
        int birthYear = cal.get(Calendar.YEAR);
        return nowYear - birthYear;
    }

    /**
     * 日期转 LocalDate
     *
     * @param date 日期
     * @return LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 日期转 LocalDateTime
     *
     * @param date 日期
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date fromDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date fromDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取时间戳（秒）
     *
     * @return 时间戳
     */
    public static long timestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取时间戳（毫秒）
     *
     * @return 时间戳
     */
    public static long timestampMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 时间戳转 Date
     *
     * @param timestamp 时间戳（秒）
     * @return Date
     */
    public static Date fromTimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * 时间戳（毫秒）转 Date
     *
     * @param timestamp 时间戳（毫秒）
     * @return Date
     */
    public static Date fromTimestampMillis(long timestamp) {
        return new Date(timestamp);
    }

    // 私有构造函数，防止实例化
    private DateUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
