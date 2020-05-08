package com.wuhe.background.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/4/30 - 16:26
 */
public class DateUtil {

    private static String FORMATTER = "yyyy-MM-dd HH:mm:ss";

    // FIXME 如果不是FORMATTER这个格式的话会出错
    public static Date str2date(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);
        return  sdf.parse(dateStr);
    }

    // 因为StringToEventConverterV2会过滤空白字符，导致传入的时间字符串日期和时间中没有空白字符
    // 这里特殊处理
    public static Date str2date4EventConverterV2(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        return  sdf.parse(dateStr);
    }
}
