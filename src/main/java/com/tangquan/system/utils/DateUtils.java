package com.tangquan.system.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午4:30
 */
public class DateUtils {

    /**
     * date转字符串
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String strDate = df.format(today);
        return strDate;
    }

    /**
     * 更新Date类型（加5分钟）
     */
    public static Date updateDate(Date date,int minute){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE,minute);
        Date newDate =cal.getTime();
        return newDate;
    }

    /**
     * 比较两个Date类型（查看是否过期）
     */
    public static boolean compareDate(Date expireTime,Date date){
        int i = date.compareTo(expireTime);
        if(i > 0){
            return true;
        }else{
            return false;
        }

    }
}
