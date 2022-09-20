package com.xktime.model.pojo.common.type;

import java.io.Serializable;

public enum HttpCodeEnum implements Serializable {

    // 成功段0
    SUCCESS(200,"操作成功"),
    FAIL(1,"请求异常"),
    NOT_FIND_ACCOUNT(2, "用户不存在"),
    LOGIN_FAIL_PASSWORD(3, "密码错误"),
    ACCOUNT_EXISTS(4, "用户已存在"),
    ILLEGAL_ARGUMENT(5, "非法参数"),
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
