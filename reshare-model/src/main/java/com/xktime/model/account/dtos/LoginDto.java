package com.xktime.model.account.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求登录dto")
public class LoginDto {
    @ApiModelProperty("账户")
    private String account;
    @ApiModelProperty("密码")
    private String password;
}
