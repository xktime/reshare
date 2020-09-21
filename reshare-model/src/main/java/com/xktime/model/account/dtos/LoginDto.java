package com.xktime.model.account.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String account;
    private String password;
}
