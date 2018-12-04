package com.xingyi.logistic.business.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by WCL on 2018/1/20.
 */
public class DateUtils
{

    /**
     * 描述信息：获取当前系统时间
     * 创建时间：2015年1月26日 上午9:12:03
     * @author WCL (ln_admin@yeah.net)
     * @return
     */
    public static String getCurrentSystemTime()
    {
        return getCurrentSystemTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 描述信息：获取当前系统时间
     * 创建时间：2015年1月26日 上午9:13:02
     * @author WCL (ln_admin@yeah.net)
     * @param format
     * @return
     */
    public static String getCurrentSystemTime(String format)
    {
        SimpleDateFormat dateFormatter =new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        return dateFormatter.format(c.getTime());
    }

    public static String formatDatetime(long time) {
        SimpleDateFormat dateFormatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return dateFormatter.format(c.getTime());
    }

    /**
     * 获取格式化日期
     * @param time 毫秒
     * @param dateFormat 日期格式
     * @return
     */
    public static String getFormatDatetime(long time, String dateFormat) {
        SimpleDateFormat dateFormatter =new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return dateFormatter.format(c.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getFormatDatetime(1519297200 * 1000L, "yyyy年M月d日 H时"));
    }
}
