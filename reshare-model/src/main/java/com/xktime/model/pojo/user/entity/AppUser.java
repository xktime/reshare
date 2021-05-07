package com.xktime.model.pojo.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import lombok.Data;

import java.util.Date;

@Data
public class AppUser {
    private long id;
    private long userId;
    private String userName;
    private String account;
    private String password;
    private String phoneNumber;
    private String profile;
    private String region;
    private int sex;
    private int isCertification;
    private int isIdentityAuthentication;
    private int status;
    private int flag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SimpleUserDto toSimpleUserDto() {
        SimpleUserDto user = new SimpleUserDto();
        user.setName(userName);
        user.setProfile(profile);
        user.setUserId(userId);
        return user;
    }
}