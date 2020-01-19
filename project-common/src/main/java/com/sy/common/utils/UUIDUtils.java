package com.sy.common.utils;


import java.util.UUID;

/**
 * @Author: shenyang
 * @Date: 2018/7/25 15:38
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
