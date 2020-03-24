package com.xktime.model.common.enums;

public enum UserStatusEnum {

    // 成功段0
    UNLOGIN(0, "用户未登录"),
    LOGGED(1, "用户已登录")
    ;

    int status;
    String message;

    UserStatusEnum(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
