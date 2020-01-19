package com.sy.common.enums;

/**
 * @Author: shenyang
 * @Date: 2020/1/19 14:16
 */
public enum StatusCodeEnum {

    REPETITIVE_OPERATION(to(310), "重复操作!"),

    OPERATION_FAILURE(to(400), "操作失败!"),

    PARAMETER_ERROR(to(405), "非法参数!"),

    CODE_ERROR(to(500), "系统异常，请联系管理员");

    private String code;
    private String message;

    StatusCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    private static String to(Integer code) {
        return "SY" + code;
    }

}
