package com.wuhe.background.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/4/30 - 16:26
 */
public class DateUtils {

    private static String FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static Date str2date(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);
        return  sdf.parse(dateStr);
    }
}
