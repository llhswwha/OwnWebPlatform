package com.shencode.ownwebplatform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtil() {
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static Date getToday() {
        return clearTime(new Date());
    }

    public static Calendar getTodayCalendar() {
        return getCalendar(getToday());
    }

    public static Date getYesterday() {
        return clearTime(getYesterdayCalendar().getTime());
    }

    public static Date getYesterday(Date date) {
        Calendar yesterdayCalendar = getCalendar(date);
        yesterdayCalendar.add(5, -1);
        return clearTime(yesterdayCalendar.getTime());
    }

    public static Calendar getYesterdayCalendar() {
        Calendar yesterdayCalendar = cloneCalendar();
        yesterdayCalendar.add(5, -1);
        yesterdayCalendar.set(11, 0);
        yesterdayCalendar.set(12, 0);
        yesterdayCalendar.set(13, 0);
        yesterdayCalendar.set(14, 0);
        return yesterdayCalendar;
    }

    public static Date getTomorrow() {
        return clearTime(getTomorrowCalendar().getTime());
    }

    public static Date getTomorrow(Date date) {
        Calendar tomorrowCalendar = getCalendar(date);
        tomorrowCalendar.add(5, 1);
        return clearTime(tomorrowCalendar.getTime());
    }

    public static Calendar getTomorrowCalendar() {
        Calendar tomorrowCalendar = cloneCalendar();
        tomorrowCalendar.add(5, 1);
        tomorrowCalendar.set(11, 0);
        tomorrowCalendar.set(12, 0);
        tomorrowCalendar.set(13, 0);
        tomorrowCalendar.set(14, 0);
        return tomorrowCalendar;
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_FORMAT);
    }

    public static String format(Date date, String pattern) {
        return format(date, new SimpleDateFormat(pattern));
    }

    public static String format(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    public static Date parseDate(String text) {
        return parse(text, DATE_FORMAT);
    }

    public static Date parseDateTime(String text) {
        return parse(text, DATE_TIME_FORMAT);
    }

    public static Date parse(String text, String pattern) {
        return parse(text, new SimpleDateFormat(pattern));
    }

    public static Date parse(String text, SimpleDateFormat format) {
        try {
            return format.parse(text);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static Date clearTime(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Calendar cloneCalendar() {
        return (Calendar)Calendar.getInstance().clone();
    }

    public static Calendar cloneCalendar(Calendar calendar) {
        return (Calendar)calendar.clone();
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = cloneCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar getCalendar(Date date, boolean clearTime) {
        Calendar calendar = getCalendar(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public static String getDay(String strBeginDate, int n) {
        Calendar calendar = getCalendar(parse(strBeginDate, "yyyyMMdd"));
        calendar.add(5, n);
        return format(calendar.getTime(), "yyyyMMdd");
    }

    public static void main(String[] args) {
        Calendar calendar = getTodayCalendar();
        calendar.add(5, -7);
        calendar.set(7, 2);
        System.out.println(formatDate(calendar.getTime()));
        calendar.add(5, 6);
        System.out.println(formatDate(calendar.getTime()));
    }
}
