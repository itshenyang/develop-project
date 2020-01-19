package com.sy.common.utils;

import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.reflection.Reflections;

/**
 * 反射
 *
 * @Author: shenyang
 * @Date: 2020/1/19 13:49
 */
public class Tr {

    /**
     * 反射获得实体类里面属性名
     */
    public static <T, R> String fnToName(Fn<T, R> fn) {
        return Reflections.fnToFieldName(fn);
    }
}
