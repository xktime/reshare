package com.xktime.model.pojo.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class BaseUser implements Serializable {
    @Id
    protected long id;
    @Field
    protected long userId;
    protected String userName;
    protected String account;
    protected String password;
}
