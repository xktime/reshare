package com.xktime.model.common.enums;

import java.io.Serializable;

public enum HttpCodeEnum implements Serializable {

    // 成功段0
    SUCCESS(0,"操作成功"),
    FAIL(1,"请求异常"),
    LOGIN_FAIL_ACCOUNT(2, "用户不存在"),
    LOGIN_FAIL_PASSWORD(3, "密码错误"),
    ;

    int code;
    String errorMessage;

    HttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
