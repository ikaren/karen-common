package org.karen.common.util;

import org.albert.common.constant.datetime.ConstFormatDateTimeString;
import org.albert.common.util.datetime.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Karen.Wang on 2016/6/14.
 */
public class AgeUtils {
    /**
     * 根据出生日期计算年龄(具体到日)
     *
     * @param birthday
     * @return -1日期有误    age>=0
     * @throws Exception
     */
    public static int getAge(Date birthday) {
        int age = -1;
        int y = DateUtil.getYear(birthday);
        int m = DateUtil.getMonthInt(birthday);
        int d = DateUtil.getDayInt(birthday);
//	  System.out.println("年："+y+"月："+m+"日："+d);
        if (y >= 0 && m >= 0 && m <= 12 && d >= 0 && d < 32) {    //判断当年月日中的任何一个小于0就会弹出重新输入，当满足条件后执行下面代码
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            month = month + 1;
            int a = year - y;    //当前年减出生年
            int b = month - m;  //当前月减出生月
            int c = day - d;    //当前日减出生日
            //当年出生的情况
            if (a > 0) {
                //当年本月以前出生的情况
                if (b > 0) {
                    a = year - y;
                }
                //当年本月今天或者今天以前
                if (b == 0 && c >= 0) {
                    a = year - y;
                }
                //当年今天以后的情况
                if (b == 0 && c < 0) {
                    a = year - y - 1;
                }
                //当年本月以后出生的情况
                if (b < 0) {
                    a = year - y - 1;
                }
            }

            //今年出生的情况
            if (a == 0) {
                //今年本月前出生情况
                if (b > 0) {
                    a = year - y;
                }
                //今年本月出生（今天或者今天以前）
                if (b == 0 && c >= 0) {
                    a = year - y;
                }
                //今年本月今天以后的情况
                if (b == 0 && c < 0) {
                    a = -1;
                }
                //今年本月以后出生情况
                if (b < 0) {
                    a = -1;
                }
            }

            //今年以后出生的情况
            if (a < 0) {
                a = -1;
            }
            age = a;
        }

        return age;

    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(ConstFormatDateTimeString.yyyy_MM_dd);
            Calendar calendar = Calendar.getInstance();
            Date date = DateUtil.getDateFromStr(format, "1991-06-15");
            System.out.println(getAge(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
