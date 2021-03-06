package com.xktime.model.pojo.user.entity;

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
    private int userType;
    private int enable;
}
