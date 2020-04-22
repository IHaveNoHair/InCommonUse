package com.cupid.demo.common.utils.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间处理类
 *
 * @author cupid
 * @date 2020-04-17
 */
public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String H3YUN_PATTERN = "yyyy-MM-dd H:mm:ss";

    public static final String DATE_WITH_ZRO_H_M_S = "yyyy-MM-dd 00:00:00";

    public static final String DATE_WITH_MAX_H_M_S = "yyyy-MM-dd 23:59:59";

    public static final String DATE_NO_SECOND_PATTERN = "yyyy-MM-dd HH:mm";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String HOUR_AND_MINUTE = "HH:mm";

    public static final String DATE_SLASH_PATTERN = "yyyy/MM/d";

    public static final String DATE_NO_SPLIT_PATTERN = "yyyyMMddHHmmss";

    public static final String DATE_CHINESE_PATTERN = "MM月dd日";

    public static final String DATE_CHINESE_DAY_PATTERN = "d号";

    public static final String DATE_CHINESE_PATTERN_WITH_YEAR = "yyyy年MM月dd日";

    public static final String DATE_CHINESE_PATTERN_WITH_MONTH = "yyyy年MM月";
    public static final String DATE_CHINESE_PATTERN_WITH_MONT1H = "yyyy年M月";

    public static final String DATE_CHINESE_ALL_PATTERN = "yyyy年MM月dd日HH点mm分ss秒";

    /**
     * 日期格式化为固定格式字符串
     * @param date 未格式化的日期数据
     * @return 返回格式化的字符串日期
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }
    /**
     * 日期格式化为指定字符串
     * @param date 未格式化的日期数据
     * @param pattern 指定时间格式
     * @return 返回格式化的字符串日期
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    /**
     * 将指定格式的字符串日期转化为Date类型
     * @param date 未转化的字符串日期
     * @return 返回转化后的日期
     * @throws ParseException 抛出转换异常
     */
    public static Date parse(String date) throws ParseException {
        return parse(date, DEFAULT_PATTERN);
    }

    /**
     * 将指定格式的字符串日期转化为指定格式的Date类型
     * @param date 未转化的字符串日期
     * @param pattern 指定日期格式
     * @return 返回转化后的日期
     * @throws ParseException 抛出转换异常
     */
    public static Date parse(String date, String pattern) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(date);
    }

    /**
     * 时间戳转化为指定格式的字符串时间
     * @param unixTimeInMilliSecond 时间戳
     * @param pattern 指定格式
     * @return 返回指定格式的字符串日期
     */
    public static String unixTimeToSimpleDateString(final long unixTimeInMilliSecond, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date(unixTimeInMilliSecond));
    }
    /**
     * 获取某一周的日期范围（比如本月第二周是从5号到11号）
     * @param weekNumber 本月第几周
     * @param date 需要是每个月的月初
     * @param map 返回值结果 （本月第几周的范围，周一是几号，周末是几号）
     */
    public static void getWeekOfMontAndWeekStartAndEnd(int weekNumber, Date date, Map<Integer, WeekRange> map) {
        int week = week(date);
        Date lastMonthDate = lastMonthDate(date);
        if(null == map){
            WeekRange range = new WeekRange(date, lastMonthDate);
            map = new HashMap<>(10);
            map.put(weekNumber, range);
        }else{
            WeekRange range = map.get(weekNumber);
            if(null == range){
                range = new WeekRange(date);
            }
            range.setEnd(date);
            map.put(weekNumber,range);
        }
        if(date.equals(lastMonthDate)){
            return ;
        }
        if(week == 0){
            weekNumber++;
        }
        getWeekOfMontAndWeekStartAndEnd(weekNumber, nextDate(date), map);
    }
    /**
     * 获取本月月初
     * @param date 当前日期
     * @return 返回本月月初
     */
    public static Date firstMonthDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //设置日期为本月一号
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    /**
     * 获取本月月初
     * @param date 当前时间
     * @return 返回本月月初
     */
    public static Date beginningOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 获取本月月末
     * @param date 当前日期
     * @return 返回本月月末
     */
    public static Date lastMonthDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取本月月末
     * @param date 当前日期
     * @return 返回本月月末
     */
    public static Date endOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 获取当前是星期几
     * @param date 日期
     * @return 返回当天是星期几
     */
    public static int week(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }
    /**
     * 获取上个月
     * @param date 当前日期
     * @return 返回上个月日期
     */
    public static Date lastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
    /**
     * 下一天日期
     * @param date 当前日期
     * @return 返回下一天日期
     */
    public static Date nextDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    /**
     * 获取距离当前日期前/后N天日期
     * @param date 当前日期
     * @param n 相距天数
     * @return 距离当前日期前/后N天日期
     */
    public static Date aroundDate(Date date, int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }
    /**
     * 获取当天 0 时
     * @param date 当天日期
     * @return 返回当天 0 时
     */
    public static Date getBeginOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天 23:59:59
     * @param date 当天日期
     * @return 返回当天 23:59:59
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 获取上一个月同一天
     * @param date 当前日期
     * @return 返回上个月同一天
     */
    public static Date lastMonthSameTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        return cal.getTime();
    }
}