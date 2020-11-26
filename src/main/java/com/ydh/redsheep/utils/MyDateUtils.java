package com.ydh.redsheep.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author annpeter.it@gmail.com
 */
public class MyDateUtils {

    private static final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final FastDateFormat HH_mm_ss_SSS = FastDateFormat.getInstance("HH:mm:ss.SSS");
    private static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter localDateSimpleFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter localDateFormatterAmPm = DateTimeFormatter.ofPattern("yyyy-MM-dda");
    private static final DateTimeFormatter localDateHourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    private static final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter localDateTimeSimpleFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static void main(String[] args) {
        String format = LocalDateTime.now().format(localDateTimeSimpleFormatter);
        System.out.println(format);
    }

    /**
     * 获取当前日期字符串对象 yyyyMMddHHmmssSSS
     *
     * @return 格式化后的日期字符串
     */
    public static String formatLocalDateYYYYMMDDHHMMSSSSS(LocalDateTime date) {
        return date.format(localDateTimeSimpleFormatter);
    }

    /**
     * 获取当前日期字符串对象 yyyy-MM-dd HH
     *
     * @return 格式化后的日期字符串
     */
    public static String formatLocalDateYYYYMMDDHH() {
        return formatLocalDateYYYYMMDDHH(null);
    }

    /**
     * 获取日期字符串对象 yyyy-MM-dd HH
     *
     * @param date 日期对象，如果为null，表示获取此刻的
     * @return 格式化后的日期字符串
     */
    public static String formatLocalDateYYYYMMDDHH(LocalDateTime date) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        return date.format(localDateHourFormatter);
    }

    public static String formatLocalDateYYYYMMDD() {
        return formatLocalDateYYYYMMDD(null);
    }

    public static String formatLocalDateYYYYMMDD_AMPM() {
        LocalDate date = LocalDate.now();
        return date.format(localDateFormatterAmPm);
    }

    /**
     * 获取日期字符串对象 yyyy-MM-dd
     *
     * @param date 日期对象，如果为null，表示获取此刻的
     * @return 格式化后的日期字符串
     */
    public static String formatLocalDateYYYYMMDD(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return date.format(localDateFormatter);
    }

    public static String formatSimpleLocalDateYYYYMMDD(LocalDate date) {
        return date.format(localDateSimpleFormatter);
    }

    public static String formatYYYYMMDDHHMMSS() {
        return formatYYYYMMDDHHMMSS(null);
    }

    /**
     * 获取日期时间字符串对象 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期对象，如果为null，表示获取此刻的
     * @return 格式化后的日期时间字符串
     */
    public static String formatYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            date = new Date();
        }
        return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String formatHHMMSS() {
        return formatHHMMSS(null);
    }

    /**
     * 获取时间字符串对象 HH:mm:ss
     *
     * @param date 日期对象，如果为null，表示获取此刻的
     * @return 格式化后的时间字符串
     */
    public static String formatHHMMSS(Date date) {
        if (date == null) {
            date = new Date();
        }
        return DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(date);
    }

    public static String formatHHMMSSSSS(Date date) {
        if (date == null) {
            date = new Date();
        }
        return HH_mm_ss_SSS.format(date);
    }

    /**
     * 将LocalTime转换为HH:mm:ss字符串
     *
     * @param time 时间
     * @return 时间格式化后的字符串
     */
    public static String localTimeToStr(LocalTime time) {
        return time.format(localTimeFormatter);
    }

    /**
     * 判断time是否在timeStart 和 timeEnd 之间
     *
     * @param timeStart 开始时间
     * @param timeEnd   结束时间
     * @param time      需要判断的中间时间
     * @return 如果time位于开始时间和结束时间之间，返回true
     */
    public static boolean judgeTimeSlot(LocalTime timeStart, LocalTime timeEnd, LocalTime time) {
        return (timeStart.isBefore(time) || timeStart.equals(time)) && (timeEnd.isAfter(time) || timeEnd.equals(time));
    }

    public static boolean judgeTimeSlotNotEqual(LocalTime timeStart, LocalTime timeEnd, LocalTime time) {
        return (timeStart.isBefore(time) && (timeEnd.isAfter(time)));
    }

    /**
     * 当前时间是否在from和to之间（闭区间）
     *
     * @param from 检查开始时间
     * @param to   检查结束时间
     * @return 如果当前时间在from和to之间，返回true
     */
    public static boolean isTimeBetween(LocalTime from, LocalTime to) {
        LocalTime now = LocalTime.now();
        return !(now.isBefore(from) || now.isAfter(to));
    }


    public static Date addDays(int offsetDay) {
        return DateUtils.addDays(new Date(), offsetDay);
    }

    public static Date addDays(Date d, int offsetDay) {
        return DateUtils.addDays(d, offsetDay);
    }

    /**
     * 获取日期时间字符串对象 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 为null,获取当前时间
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        if(dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        return dateTime.format(localDateTimeFormatter);
    }

    public static LocalDateTime toLocalDateTimeYYYYMMddHHmmss(String timeStr) {
        return LocalDateTime.parse(timeStr, localDateTimeFormatter);
    }
    public static LocalDate toLocalDateYYYYMMdd(String timeStr) {
        return LocalDate.parse(timeStr, localDateFormatter);
    }
    public static LocalDate toSimpleLocalDateYYYYMMdd(String timeStr) {
        return LocalDate.parse(timeStr, localDateSimpleFormatter);
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年份
     * @return true是闰年, false不是闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 计算两个时间点的天数差
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即要计算的天数差
     */
    public static int dateDiff(LocalDateTime dt1,LocalDateTime dt2){
        //获取第一个时间点的时间戳对应的秒数
        long t1 = dt1.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第一个时间点在是1970年1月1日后的第几天
        long day1 = t1 /(60*60*24);
        //获取第二个时间点的时间戳对应的秒数
        long t2 = dt2.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第二个时间点在是1970年1月1日后的第几天
        long day2 = t2/(60*60*24);
        //返回两个时间点的天数差
        return (int)(day2 - day1);
    }

    /**
     * 当前时间是否在from和to之间（闭区间）
     *
     * @param from 检查开始时间
     * @param to   检查结束时间
     * @return 如果当前时间在from和to之间，返回true
     */
    public static boolean isTimeBetween(LocalDateTime from, LocalDateTime to) {
        LocalDateTime now = LocalDateTime.now();
        return !(now.isBefore(from) || now.isAfter(to));
    }

    public static boolean isTimeBetween(LocalDateTime from, LocalDateTime to,LocalDateTime current) {
        return !(current.isBefore(from) || current.isAfter(to));
    }
}
