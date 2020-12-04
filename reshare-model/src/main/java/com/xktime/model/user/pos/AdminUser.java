package com.xktime.model.user.pos;

import lombok.Data;

@Data
public class AdminUser {
    private Long id;
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
