package com.xktime.model.common.enums;

public enum HttpCodeEnum {

    // 成功段0
    SUCCESS(0,"操作成功"),
    FAIL(1,"操作失败"),
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
