package com.sy.common.utils;


import com.alibaba.fastjson.JSON;

/**
 * @Author: shenyang
 * @Date: 2020/1/16 16:17
 */
public class JSONUtils {

    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }
}
