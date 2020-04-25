package com.xktime.model.common.enums;

public enum AppHttpCodeEnum {

    // 成功段0
    SUCCESS(0,"操作成功"),
    ;

    int code;
    String errorMessage;

    AppHttpCodeEnum(int code, String errorMessage){
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
