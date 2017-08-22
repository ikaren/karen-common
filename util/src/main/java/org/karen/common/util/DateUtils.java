package org.karen.common.util;

import org.albert.common.util.datetime.DateUtil;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 扩展时间工具类
 * Created by karen on 16/11/9.
 */
public class DateUtils extends DateUtil {

    /**
     * 计算时间间隔
     *
     * @param start_date
     * @param end_date
     * @param format     y年，M月，d日，H小时，m分钟，s秒
     * @return
     */
    public static int getInterval(String start_date, String end_date, String format) {
        int d = 0;
        if (start_date != null && end_date != null) {
            Date date1 = getDateFromStr(format_yyyyMMdd_HHmmss, start_date);
            Date date2 = getDateFromStr(format_yyyyMMdd_HHmmss, end_date);
            d = getInterval(date1, date2, format);
        }

        return d;
    }

    /**
     * 计算时间间隔
     *
     * @param date1
     * @param date2
     * @param format y年，M月，d日，H小时，m分钟，s秒
     * @return
     */
    public static int getInterval(Date date1, Date date2, String format) {
        if (date1 != null && date2 != null) {
            boolean d = false;
            int d1;
            if (date1.compareTo(date2) < 0) {
                d1 = Integer.parseInt(DurationFormatUtils.formatPeriod(date1.getTime(), date2.getTime(), format));
            } else {
                d1 = 0 - Integer.parseInt(DurationFormatUtils.formatPeriod(date2.getTime(), date1.getTime(), format));
            }

            return d1;
        } else {
            return 0;
        }
    }

    /**
     * 返回小时，整形
     *
     * @param date Date 日期
     * @return 返回小时，零点返回值为0
     */
    public static int getHourInt(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟，整形
     *
     * @param date Date 日期
     * @return 返回分钟，零分返回值为0
     */
    public static int getMinuteInt(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟，整形
     *
     * @param date Date 日期
     * @return 返回秒钟，零秒返回值为0
     */
    public static int getSecondInt(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒钟，整形
     *
     * @param date Date 日期
     * @return 返回毫秒秒钟，零点返回值为"00"
     */
    public static int getMilliSecondInt(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MILLISECOND);
    }

    /**
     * 返回小时，字符串
     *
     * @param date Date 日期
     * @return 返回小时，零点返回值为"00"
     */
    public static String getHourStr(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        return hour < 10 ? "0" + hour : String.valueOf(hour);
    }

    /**
     * 返回分钟，字符串
     *
     * @param date Date 日期
     * @return 返回分钟，零点返回值为"00"
     */
    public static String getMinuteStr(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int minute = ca.get(Calendar.MINUTE);
        return minute < 10 ? "0" + minute : String.valueOf(minute);
    }

    /**
     * 返回秒钟，字符串
     *
     * @param date Date 日期
     * @return 返回秒钟，零点返回值为"00"
     */
    public static String getSecondStr(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int second = ca.get(Calendar.SECOND);
        return second < 10 ? "0" + second : String.valueOf(second);
    }

    /**
     * 返回秒钟，字符串
     *
     * @param date Date 日期
     * @return 返回毫秒秒钟，长度为3位，2位为010，零点返回值为"000"
     */
    public static String getMilliSecondStr(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int second = ca.get(Calendar.MILLISECOND);
        if (second < 10) {
            return "00" + second;
        } else if (second < 100) {
            return "0" + second;
        } else {
            return String.valueOf(second);
        }
    }

    /**
     * 返回指定日期的n分钟之后
     *
     * @param date
     * @param n    分钟数
     * @return Date n分钟之后的日期
     */
    public static Date aftMins(Date date, int n) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DATE);
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int min = ca.get(Calendar.MINUTE) + n;
        int sec = ca.get(Calendar.SECOND);
        String aftStr = year + (month < 10 ? "-0" : "-") + month
                + (day < 10 ? "-0" : "-") + day + " " + (hour < 10 ? "0" : "")
                + hour + ":" + (min < 10 ? "0" : "") + min + ":"
                + (sec < 10 ? "0" : "") + sec;
        Date aftDate = DateUtil.getDateFromStr(format_yyyyMMdd_HHmmss, aftStr);
        return aftDate;
    }

    /**
     * 组装时间，取ymd的年月日，HmsSSS的时分秒毫秒组装成新的时间
     *
     * @param yMd    组装年月日的日期
     * @param HmsSSS 组装时分秒毫秒的日期
     * @return
     */
    public static Date assembleDate(Date yMd, Date HmsSSS) {
        String dateStr = format_yyyyMMdd.format(yMd) + " " +
                DateUtils.getHourStr(HmsSSS) + ":" +
                DateUtils.getMinuteStr(HmsSSS) + ":" +
                DateUtils.getSecondStr(HmsSSS) +
                DateUtils.getMilliSecondStr(HmsSSS);
        Date date = DateUtil.getDateFromStr(format18, dateStr);
        return date;
    }

    /**
     * 组装时间，取ymd的年月日，HmsSSS的时分秒毫秒组装成新的时间
     *
     * @param yMd    组装年月日的日期
     * @param HmsSSS 时分秒毫秒的字符串，格式"HH:mm:ssSSS"
     * @return
     */
    public static Date assembleDateHmsSSS(Date yMd, String HmsSSS) {
        String dateStr = format_yyyyMMdd.format(yMd) + " " + HmsSSS;
        Date date = DateUtil.getDateFromStr(format18, dateStr);
        return date;
    }

    /**
     * 组装时间，取ymd的年月日，Hms的时分秒组装成新的时间
     *
     * @param yMd 组装年月日的日期
     * @param Hms 时分秒的字符串，格式"HH:mm:ss"
     * @return
     */
    public static Date assembleDateHms(Date yMd, String Hms) {
        String dateStr = format_yyyyMMdd.format(yMd) + " " + Hms;
        Date date = DateUtil.getDateFromStr(format_yyyyMMdd_HHmmss, dateStr);
        return date;
    }

    /**
     * 比较2个日期相差天数
     * 忽略时分秒，只比较年月日
     * 例如：2016-1-1 10:00:00，2016-1-2 08:00:00，返回1天。
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getIntervalOfDay(Date date1, Date date2) {
        date1 = assembleDateHmsSSS(date1, "00:00:00000");
        date2 = assembleDateHmsSSS(date2, "00:00:00000");
        return getInterval(date1, date2, "d");
    }

    /**
     * 比较date与start，end的关系
     * 返回值说明：
     * 0 未匹配
     * 1 date<start
     * 2 date=start
     * 3 start<date<end
     * 4 date=end
     * 5 date>end
     *
     * @param start
     * @param end
     * @param date
     * @return
     */
    public static int compareDate(Date start, Date end, Date date) {
        int i = 0;
        if (date.before(start)) {
            i = 1;
        } else if (date.equals(start)) {
            i = 2;
        } else if (date.after(start) && date.before(end)) {
            i = 3;
        } else if (date.equals(end)) {
            i = 4;
        } else if (date.after(end)) {
            i = 5;
        }
        return i;
    }


}
