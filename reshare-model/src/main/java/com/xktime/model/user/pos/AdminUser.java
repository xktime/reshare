package com.xktime.model.user.pos;

import lombok.Data;

@Data
public class AdminUser {
    private long id;
    private long userId;
    private String userName;
    private String account;
    private String password;
    private int sex;
    private String deptId;
    private int locked;
    private String remark;
    private byte userType;
    private byte enable;
}
