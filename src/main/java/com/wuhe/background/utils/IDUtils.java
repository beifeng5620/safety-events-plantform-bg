package com.wuhe.background.utils;

import java.util.UUID;

/**
 * @author wuhe
 * @Date 2020/5/6 - 1:39
 */
public class IDUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
