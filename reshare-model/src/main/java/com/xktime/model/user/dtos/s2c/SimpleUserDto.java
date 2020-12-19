package com.xktime.model.user.dtos.s2c;

import lombok.Data;

@Data
public class SimpleUserDto {
    private long userId;
    private String name;
    private String profile;
}
