package com.wuhe.background.util;

import java.util.UUID;

/**
 * @author wuhe
 * @Date 2020/5/6 - 1:39
 */
public class IDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
