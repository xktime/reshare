package com.xktime.model.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseUser implements Serializable {
    protected long id;
    protected long userId;
    protected String userName;
    protected String account;
    protected String password;
}
