package com.sy.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: shenyang
 * @Date: 2020/1/19 14:02
 */
@Setter
@Getter
public class ApiResult<T> implements Serializable {

    private boolean status = false;
    private String message;
    private T result;
    private String statusCode;

    public static ApiResult error(String statusCode, String message) {
        return new ApiResult(message, null, statusCode);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(true, "操作成功", data, "SYS000");
    }

    public ApiResult() {
    }

    public ApiResult(String message, T result, String statusCode) {
        this.message = message;
        this.result = result;
        this.statusCode = statusCode;
    }

    public ApiResult(boolean status, String message, T result, String statusCode) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.statusCode = statusCode;
    }
}
