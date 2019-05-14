/*
 * Copyright (c) 2008-2014 浩瀚深度 All Rights Reserved.
 *
 * FileName: DateUtil.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zmw, 2014年3月14日, Create
 */
package com.hh.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间转换工具类,定义进行时间转换，获取，日期转换等
 * @author zmw
 * @version 0.9.0
 * @since 0.9.0
 *
 */
public class DateUtil
{
    /**
     * 工具类不能创建实例
     */
    private DateUtil()
    {
    }
    /**
     * 获取当前秒
     * @return 秒
     */
    public static int getSeconds()
    {
        Calendar cld = Calendar.getInstance();
        return (int) Math.round(cld.getTimeInMillis() / 1000.0);
    }
    /**
     * 根据指定日期获取时间
     * @param date 指定日期
     * @return 返回该日期的秒
     */
    public static int getSeconds(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return (int) Math.round(cld.getTimeInMillis() / 1000.0);
    }
    /**
     * 根据指定日期获取时间
     * @param cld 指定日期
     * @return 返回该日期的秒
     */
    public static int getSeconds(Calendar cld)
    {
        return (int) Math.round(cld.getTimeInMillis() / 1000.0);
    }
    /**
     * 根据指定日期获取时间
     * @param snmpTimeString 指定日期字符串
     * @return 返回该日期的秒
     */
    public static int getSeconds(String snmpTimeString)
    {
        return getSeconds(getDate(snmpTimeString));
    }
    /**
     * 返回指定日期
     * @return 返回当前日期
     */
    public static Date getDate()
    {
        Calendar cld = Calendar.getInstance();
        return cld.getTime();
    }
    /**
     * 根据整形的UTC秒数获取Date
     * @param sec UTC时间，单位秒
     * @return Date
     */
    public static Date getDate(int sec)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis((long) sec * 1000);
        return cld.getTime();
    }
    /**
     * 据Calendar获取Date
     * @param cld Calendar
     * @return Date
     */
    public static Date getDate(Calendar cld)
    {
        return cld.getTime();
    }

    /**
     * @param y year
     * @param m month in a year 1-12
     * @param d day in a month 1-31
     * @param h hour in a day 0-23
     * @param min minute in an hour 0-59
     * @param s second in a minute 0-59
     * @return Date
     */
    public static Date getDate(int y, int m, int d, int h, int min, int s)
    {
        Calendar cld = Calendar.getInstance();
        cld.set(y, m - 1, d, h, min, s);
        return cld.getTime();
    }
    /**
     * 根据指定日期字符串转换为Date
     * @param snmpTimeString 格式化的日期字符串，YYYY.MM.DD hh:mm:ss
     * @return Date
     */
    public static Date getDate(String snmpTimeString)
    {
        Date date = null;
        if (snmpTimeString == null || snmpTimeString.equals(""))
        {
            snmpTimeString = "0.0.0 0:0:0";
        }
        String[] temp = snmpTimeString.split(" ");
        String[] d = new String[3];
        d[0] = temp[0].substring(0, temp[0].indexOf('.'));
        d[1] = temp[0].substring(temp[0].indexOf('.') + 1, temp[0].lastIndexOf('.'));
        d[2] = temp[0].substring(temp[0].lastIndexOf('.') + 1, temp[0].length());

        String[] time = temp[1].split(":");

        int year = Integer.valueOf(d[0]).intValue();
        int month = Integer.valueOf(d[1]).intValue();
        int day  = Integer.valueOf(d[2]).intValue();
        int hour = Integer.valueOf(time[0]).intValue();
        int minute = Integer.valueOf(time[1]).intValue();
        int second = Integer.valueOf(time[2]).intValue();
        date = getDate(year, month, day, hour, minute, second);
        return date;
    }
    /**
     * 获取当前Calendar
     * @return Calendar
     */
    public static Calendar getCalendar()
    {
        Calendar cld = Calendar.getInstance();
        return cld;
    }
    /**
     * 根据指定的时间获取Calendar
     * @param sec UTC时间，秒
     * @return Calendar
     */
    public static Calendar getCalendar(int sec)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis((long) (sec * 1000));
        return cld;
    }
    /**
     * 根据指定的时间获取Calendar
     * @param date 指定的Date
     * @return Calendar
     */
    public static Calendar getCalendar(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld;
    }
    /**
     * 根据指定的日期获取Calendar
     * @param snmpTimeString 格式化的日期字符串，YYYY.MM.DD hh:mm:ss
     * @return Calendar
     */
    public static Calendar getCalendar(String snmpTimeString)
    {
        return getCalendar(getDate(snmpTimeString));
    }
    /**
     * 获取格式化的日期字符串，YYYY.MM.DD hh:mm:ss
     * @return YYYY.MM.DD hh:mm:ss
     */
    public static String getTabnameDateString()
    {
        return getTabnameDateString(getDate());
    }
    /**
     * 根据指定的时间获取格式化的日期字符串
     * @param sec UTC时间，秒
     * @return YYYY.MM.DD hh:mm:ss
     */
    public static String getTabnameDateString(int sec)
    {
        return getTabnameDateString(getDate(sec));
    }
    /**
     * 根据指定的时间获取格式化的日期字符串
     * @param date Date
     * @return YYYY.MM.DD hh:mm:ss
     */
    public static String getTabnameDateString(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return "" + (cld.get(Calendar.YEAR)) +
                 getDateString(cld.get(Calendar.MONTH) + 1) +
                 getDateString(cld.get(Calendar.DAY_OF_MONTH));
    }
    /**
     * 根据指定的时间获取格式化的日期字符串
     * @param sec UTC时间，秒
     * @return YYYY.MM.DD hh
     */
    public static String getTabnameHourString(int sec)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(getDate(sec));
        return "" + (cld.get(Calendar.YEAR)) +
                 getDateString(cld.get(Calendar.MONTH) + 1) +
                 getDateString(cld.get(Calendar.DAY_OF_MONTH)) +
                 getDateString(cld.get(Calendar.HOUR_OF_DAY));
    }
    /**
     * 根据指定的时间获取格式化的日期字符串
     * @param cld Calendar
     * @return YYYY.MM.DD hh:mm:ss
     */
    public static String getTabnameDateString(Calendar cld)
    {
        return getTabnameDateString(cld.getTime());
    }
    /**
     *  获取日期的时间字串，年月日间不加分隔符，不足位的要以0补齐
     * @param cld Calendar
     * @return YYYYMMDD
     */
    public static String getFilenameDateString(Calendar cld)
    {
        String year = "" + cld.get(Calendar.YEAR);
        return "" + year.substring(2, 4) +
               (cld.get(Calendar.MONTH) + 1 > 9 ? "" : "0") + (cld.get(Calendar.MONTH) + 1) +
               (cld.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") + cld.get(Calendar.DAY_OF_MONTH);
    }
    /**
     *  获取日期的时间字串，年月日间不加分隔符，不足位的要以0补齐
     * @param sec UTC时间
     * @return YYYYMMDDhhmm
     */
    public static String getFilenameDateString(int sec)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(getDate(sec));
        return "" + cld.get(Calendar.YEAR) +
               (cld.get(Calendar.MONTH) + 1 > 9 ? "" : "0") + (cld.get(Calendar.MONTH) + 1) +
               (cld.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") + cld.get(Calendar.DAY_OF_MONTH) +
               (cld.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") + cld.get(Calendar.HOUR_OF_DAY) +
               (cld.get(Calendar.MINUTE) > 9 ? "" : "0") + cld.get(Calendar.MINUTE);
    }
    /**
     *  获取日期的时间字串，年月日间不加分隔符，不足位的要以0补齐
     * @return YYYYMMDDhhmm
     */
    public static String getFilenameDateString()
    {
        return getFilenameDateString(Calendar.getInstance());
    }
    /**
     * 获取月度报表文件名称
     * @param prefix 文件名前缀
     * @param date Date
     * @deprecated 不在使用
     * @return Filename_YYYY_MM
     */
    @Deprecated
    public static String getMonthReportName(String prefix, Date date)
    {
        return prefix + "_" + getYear(date) +
                "_" + getDateString(getMonth(date));
    }
    /**
     *  获取周度报表文件名称
     * @param prefix 文件名前缀
     * @param date Date
     * @deprecated 不在使用
     * @return Filename_YYYY_WW
     */
    @Deprecated
    public static String getWeekReportName(String prefix, Date date)
    {
//        DateNumCaster caster = new DateNumCaster();
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return prefix + "_" + cld.get(Calendar.YEAR) + "_W" + cld.get(Calendar.WEEK_OF_YEAR);
    }
    /**
     *  获取日度报表文件名称
     * @param prefix 文件名前缀
     * @param date Date
     * @deprecated 不在使用
     * @return Filename_YYYY_MM_DD
     */
    @Deprecated
    public static String getDayReportName(String prefix, Date date)
    {
//        DateNumCaster caster = new DateNumCaster();
        return prefix + "_" + getTabnameDateString(date);
    }
    /**
     * 获取time所在日的起始UTC秒
     * @param time UTC时间，单位秒
     * @return time所在日的0点0分0秒的时间，UTC，单位秒
     */
    public static int getDayBeginTime(int time)
    {
        Date date = getDate(time);
        int year  = getYear(date);
        int month = getMonth(date);
        int day = getDayOfMonth(date);
        Date beginDate = getDate(year, month, day, 0, 0, 0);
        return (int) (beginDate.getTime() / 1000);
    }
    /**
     * 获取time所在月的第一天起始UTC秒
     * @param time UTC时间，单位秒
     * @return time所在月的起始时间，UTC，单位秒
     */
    public static int getMonthBeginTime(int time)
    {
        int year  = getYear(getDate(time));
        int month = getMonth(getDate(time));
        Date beginDate = getDate(year, month, 1, 0, 0, 0);
        return (int) (beginDate.getTime() / 1000);
    }
    /**
     *  获取time所在月最后一天的起始UTC秒
     * @param time UTC时间，单位秒
     * @return time所在月的最后一天的起始时间，UTC，单位秒
     */
    public static int getMonthEndTime(int time)
    {
        int year  = getYear(getDate(time));
        int month = getMonth(getDate(time));
        int day   = getDayOfMonth(getDate(time));
        Date theDate = getDate(year, month, day, 0, 0, 0);
        int tmpTime = (int) (theDate.getTime() / 1000);
        while (getMonth(getDate(tmpTime)) == month)
        {
            tmpTime += 86400;
        }
        tmpTime -= 86400;
        return tmpTime;
    }
    /**
     * 获取date所在周的起始UTC秒,date必须是该周最后一天的0点
     * @param date Date
     * @return date所在周的起始UTC秒
     */
    public static int getWeekBeginTime(Date date)
    {
        long time = date.getTime() / 1000;
        return (int) (time - 86400 * 6);
    }
    /**
     * 获取date所在月的起始UTC秒,date必须是该月最后一天的0点
     * @param date Date
     * @return date所在月的起始UTC秒
     */
    public static int getMonthBeginTime(Date date)
    {
        int counter = 0;
        int month = getMonth(date);
        long time = date.getTime() / 1000;
        long tmpTime = time;
        while (true)
        {
            tmpTime -= 86400;
            Date theDate = new Date(tmpTime * 1000);
            if (getMonth(theDate) != month)
            {
                break;
            }
            counter++;
        }
        return (int) (time - 86400 * counter);
    }
    /**
     * 获取time所在月份每天的字符串形式2006-01-01
     * @param time UTC时间，秒
     * @return 所在月份每天的字符串形式2006-01-01
     */
    public static String[] getOneMonthDaysStr(int time)
    {
        int year  = getYear(getDate(time));
        int month = getMonth(getDate(time));
        Date beginDate = getDate(year, month, 1, 0, 0, 0);
        int tmpTime = (int) (beginDate.getTime() / 1000);
        ArrayList<String> dateList = new ArrayList<String>();
        while (getMonth(getDate(tmpTime)) == month)
        {
            String dateStr = intTime2StringDate(tmpTime);
            dateList.add(dateStr);
            tmpTime += 86400;
        }
        String[] retData = new String[dateList.size()];
        for (int i = 0; i < dateList.size(); i++)
        {
            retData[i] = (String) dateList.get(i);
        }
        return retData;
    }
    /**
     * 获取time所在月份的天数
     * @param time UTC时间，秒
     * @return 所在月份的天数
     */
    public static int getMonthDayNum(int time)
    {
        int year  = getYear(getDate(time));
        int month = getMonth(getDate(time));
        Date beginDate = getDate(year, month, 1, 0, 0, 0);
        int tmpTime = (int) (beginDate.getTime() / 1000);
        int dayNum = 0;
        while (getMonth(getDate(tmpTime)) == month)
        {
            dayNum++;
            tmpTime += 86400;
        }
        return dayNum;
    }
    /**
     * 获取某时间段内的工作日天数（含起止时间）
     * @param beginTime 统计的起始时间，UTC，单位秒
     * @param endTime 统计的结束时间，UTC，单位秒
     * @return 时间段内的工作日天数（含起止时间）
     */
    public static int getWorkDayNums(long beginTime, long endTime)
    {
        int counter = 0;
        Calendar cld = Calendar.getInstance();
        while (beginTime <= endTime)
        {
            cld.setTimeInMillis(beginTime * 1000);
            if (cld.get(Calendar.DAY_OF_WEEK) >= 2 && cld.get(Calendar.DAY_OF_WEEK) <= 6)
            {
                counter++;
            }
            beginTime += 86400;
        }
        return counter;
    }
    /**
     *  获取某时间段内的休息日天数（含起止时间）
     * @param beginTime 统计的起始时间，UTC，单位秒
     * @param endTime 统计的结束时间，UTC，单位秒
     * @return 时间段内的休息日天数（含起止时间）
     */
    public static int getWeekEndNums(long beginTime, long endTime)
    {
        int counter = 0;
        Calendar cld = Calendar.getInstance();
        while (beginTime <= endTime)
        {
            cld.setTimeInMillis(beginTime * 1000);
            if (cld.get(Calendar.DAY_OF_WEEK) < 2 || cld.get(Calendar.DAY_OF_WEEK) > 6)
            {
                counter++;
            }
            beginTime += 86400;
        }
        return counter;
    }
    /**
     * 根据date获取指定的日期所在年份
     * @param date Date
     * @return 指定的日期所在年份
     */
    public static int getYear(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.YEAR);
    }
    /**
     * 根据date获取指定的日期所在月份
     * @param date Date
     * @return 指定的日期所在月份
     */
    public static int getMonth(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.MONTH) + 1;
    }
    /**
     * 根据date获取指定的日期所在月份的字符串
     * @param date Date
     * @return 指定的日期所在月份的字符串，YYYYMM
     */
    public static String getMonthStr(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return "" + (cld.get(Calendar.YEAR)) +
                 getDateString(cld.get(Calendar.MONTH) + 1);
    }
    /**
     * 根据date获取指定的日期所在季度
     * @param date Date
     * @return 获取指定的日期所在季度
     */
    public static int getQuarter(Date date)
    {
        int month = getMonth(date);
        if (month >= 1 && month <= 3)
        {
            return 1;
        }
        else if (month >= 4 && month <= 6)
        {
            return 2;
        }
        else if (month >= 7 && month <= 9)
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }
    /**
     * 根据date获取指定的日期所在月份
     * @param date Date
     * @return 指定的日期所在月份
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 根据date获取指定的日期所在周的天数
     * @param date Date
     * @return 指定的日期所在周的天数，monday-->sunday: 1-->7
     */
    public static int getDayOfWeek(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        if (cld.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            return 7;
        }
        else
        {
            return cld.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }
    /**
     * 根据date获取指定的日期所在月的周数
     * @param date Date
     * @return 指定的日期所在月的周数
     */
    public static int getWeekOfMonth(Date date)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.WEEK_OF_MONTH);
    }
    /**
     * 根据date获取指定的日期是所在年的第几周
     * @param date Date
     * @return 指定的日期是所在年的第几周
     */
    public static int getWeekOfYear(Date date)
    {
        // 按照C语言的方式，计算指定时间所在的周数
        Calendar cld = Calendar.getInstance();
        cld.setFirstDayOfWeek(Calendar.MONDAY); // 设置周一为一周第一天
        cld.setMinimalDaysInFirstWeek(7); // 设置每周最少7天
        cld.setTime(date);
        int retValue = cld.get(Calendar.WEEK_OF_YEAR);
        if (cld.get(Calendar.MONTH) == Calendar.JANUARY && retValue >= 52) // 如果起始周为半周，则周编号为0
        {
            retValue = 0;
        }
        return retValue;
    }
    /**
     * 获取指定的日期所在的起始小时时间
     * @param minutes 指定的时间，UTC，单位分钟
     * @return 指定的日期所在的起始小时时间
     */
    public static int getSecAfterMinutes(int minutes)
    {
        if (minutes > 59)
        {
            minutes = minutes % 60;
        }
        Calendar cld = Calendar.getInstance();
        Date d =  DateUtil.getDate(cld.get(Calendar.YEAR),
                                        cld.get(Calendar.MONTH) + 1,
                                        cld.get(Calendar.DAY_OF_MONTH),
                                        cld.get(Calendar.HOUR_OF_DAY),
                                        cld.get(Calendar.MINUTE) - cld.get(Calendar.MINUTE) % minutes,
                                        0);
        return DateUtil.getSeconds(d) + minutes * 60;
    }
    /**
     * 获取指定的日期所在的起始日时间
     * @param hours 指定的时间，UTC，单位小时
     * @return 指定的日期所在的起始日时间 YYYYMMDD 00:00:00表示的秒
     */
    public static int getSecAfterHours(int hours)
    {
        if (hours > 23)
        {
            hours = hours % 24;
        }
        Calendar cld = Calendar.getInstance();
        Date d =  DateUtil.getDate(cld.get(Calendar.YEAR),
                                        cld.get(Calendar.MONTH) + 1,
                                        cld.get(Calendar.DAY_OF_MONTH),
                                        cld.get(Calendar.HOUR_OF_DAY),
                                        0, 0);
        return DateUtil.getSeconds(d) + hours * 3600;
    }
    /**
     * 补足时间字符串，小于10的第一位补0
     * @param number 指定的数字
     * @return 指定的日期所在的起始日时间 YYYYMMDD 00:00:00表示的秒
     */
    public static String getDateString(int number)
    {
        if (number < 10)
        {
            return "0" + number;
        }
        else
        {
            return "" + number;
        }
    }
    /**
     * 获取当前日的0点时间
     * @return Date
     */
    public static Date getToday()
    {
        Calendar cld = Calendar.getInstance();
        Date d = DateUtil.getDate(cld.get(Calendar.YEAR),
                cld.get(Calendar.MONTH) + 1, cld.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return d;
    }
    /**
     * 计算公历假期
     * @param time 
     * @return 1-假期，0-非假期
     * @deprecated 废弃，since 1.0.0
     */
    @Deprecated
    public static int isHoliday(long time)
    {
        Date theDate = new Date(time * 1000);
        int month  = DateUtil.getMonth(theDate);
        int date   = DateUtil.getDayOfMonth(theDate);
        int day    = DateUtil.getDayOfWeek(theDate);
        if (day >= 6) // weekend
        {
            return 1;
        }
        if (month == 1 && date <= 3)
        {
            return 1;
        }
        if (month == 5 && date <= 7)
        {
            return 1;
        }
        if (month == 10 && date <= 7)
        {
            return 1;
        }
        return 0;
    }
    /**
     *  判断是否为数据库维护时间,缺省0点为维护时间，允许有滞后10秒偏差
     * @param now 当前时间，Date
     * @return true - 维护时间；false - 非维护时间
     */
    public static boolean isMaintainTime(Date now)
    {
        boolean retData = false;
        Calendar time = Calendar.getInstance();
        time.setTime(now);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int min  = time.get(Calendar.MINUTE);
        int scd  = time.get(Calendar.SECOND);
        if (hour == 0 && min == 0 && scd <= 10)
        {
            retData = true;
        }
        return retData;
    }
    /**
     * 将整数形式的秒转换为日期时间的字符串
     * @param time 指定时间，单位秒
     * @return YYYY-MM-DD hh:mm:ss
     */
    public static String intTime2String(int time)
    {
        String strLastTime = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            strLastTime = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                    (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                    lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                    lastime.get(Calendar.HOUR_OF_DAY) + ":" + (lastime.get(Calendar.MINUTE) > 9 ? "" : "0") +
                    lastime.get(Calendar.MINUTE) + ":" + (lastime.get(Calendar.SECOND) > 9 ? "" : "0") +
                    lastime.get(Calendar.SECOND);
        }
        return strLastTime;
    }
    /**
     * 将毫秒转换为日期时间的字符串
     * @param time 毫秒
     * @return YYYY-MM-DD hh:mm
     */
    public static String longTime2String(long time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis(time);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                   (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                   lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                   lastime.get(Calendar.HOUR_OF_DAY) + ":" + (lastime.get(Calendar.MINUTE) > 9 ? "" : "0") +
                   lastime.get(Calendar.MINUTE);
        }
        return lastTimeStr;
    }

/*    *//**
     * 将整数形式的秒转换为日期的字符串yyyy_mm_dd hh_mm_ss;
     * @param time 整数形式的秒
     * @return YYYY-MM-DD hh:mm:ss
     *//*
    public static String intTimeToStringData(int time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                   (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                   lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                   lastime.get(Calendar.HOUR_OF_DAY) + ":" + (lastime.get(Calendar.MINUTE) > 9 ? "" : "0") +
                   lastime.get(Calendar.MINUTE) + ":" + (lastime.get(Calendar.SECOND) > 9 ? "" : "0") +
                   lastime.get(Calendar.SECOND);
        }
        return lastTimeStr;
    }*/
    /**
     * 将整数形式的秒转换为日期的字符串yyyy_mm_dd hh;
     * @param time 整数形式的秒
     * @return YYYY_MM_DD hh
     */
    public static String intTimeToString(int time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                   (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                   lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                   lastime.get(Calendar.HOUR_OF_DAY);
        }
        return lastTimeStr;
    }
    /**
     * 将整型的秒转换为日期的字符串yyyy_mm_dd hh:00:01 - yyyy_mm_dd hh+1:00:00
     * @param time 整型的秒
     * @return yyyy_mm_dd hh:00:01 - yyyy_mm_dd hh+1:00:00
     */
    public static String getCurrentHourString(int time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                   (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                   lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                   lastime.get(Calendar.HOUR_OF_DAY) + ":00:01 - " + lastime.get(Calendar.YEAR) + "-" +
                   (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") + (lastime.get(Calendar.MONTH) + 1) + "-" +
                   (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") + lastime.get(Calendar.DAY_OF_MONTH) + " " +
                   (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") + (lastime.get(Calendar.HOUR_OF_DAY) + 1) +
                   ":00:00";
        }
        return lastTimeStr;
    }
    /**
     * 将整型的秒转换为日期的字符串yyyy_mm_dd 00:00:01 - yyyy_mm_dd+1 00:00:00
     * @param time 整型的秒
     * @return yyyy_mm_dd 00:00:01 - yyyy_mm_dd+1 00:00:00
     */
    public static String getCurrentDayString(int time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                   (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                   lastime.get(Calendar.DAY_OF_MONTH) + " " + "00:00:01 - " + lastime.get(Calendar.YEAR) + "-" +
                   (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") + (lastime.get(Calendar.MONTH) + 1) + "-" +
                   (lastime.get(Calendar.DAY_OF_MONTH) > 8 ? "" : "0") + (lastime.get(Calendar.DAY_OF_MONTH) + 1) +
                   " " + "00:00:00";
        }
        return lastTimeStr;
    }
    /**
     * 将整数形式的秒转换为日期的字符串yyyy_mm_dd
     * @param time 整数形式的秒
     * @return yyyy-mm-dd
     */
    public static String intTime2StringDate(int time)
    {
        String lastTimeStr = "";
        if (time > 0)
        {
            Calendar lastime = Calendar.getInstance();
            lastime.setTimeInMillis((long) time * 1000);
            lastTimeStr = "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                    (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                    lastime.get(Calendar.DAY_OF_MONTH);
        }
        return lastTimeStr;
    }
    /**
     * 得到Server端的毫秒数
     * @return Server端的毫秒数
     */
    public static long getServerMS()
    {
        return Calendar.getInstance().getTimeInMillis();
    }
    /**
     * 得到Server端的时间,自己使用的
     * @return Server端的时间
     */
    public static String getServerTime()
    {
        Calendar lastime = Calendar.getInstance();
        return "" + lastime.get(Calendar.YEAR) + "-" + (lastime.get(Calendar.MONTH) + 1 > 9 ? "" : "0") +
                (lastime.get(Calendar.MONTH) + 1) + "-" + (lastime.get(Calendar.DAY_OF_MONTH) > 9 ? "" : "0") +
                lastime.get(Calendar.DAY_OF_MONTH) + " " + (lastime.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0") +
                lastime.get(Calendar.HOUR_OF_DAY) + ":" + (lastime.get(Calendar.MINUTE) > 9 ? "" : "0") +
                lastime.get(Calendar.MINUTE) + ":" +  lastime.get(Calendar.SECOND) + ":" +
                lastime.get(Calendar.MILLISECOND);
    }

    /**
     * 获取整分钟数
     * @return
     */
    public static long getReorganizeMinute(){
        Date date = new Date();
        long time = date.getTime()/1000;
        time -= time%60;
        return time;
    }

    public static String tableSummaryTime(){
        Calendar lastime = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(lastime.getTime());
    }
    public static void main(String[] args) {
        System.out.println(tableSummaryTime());
    }
}
