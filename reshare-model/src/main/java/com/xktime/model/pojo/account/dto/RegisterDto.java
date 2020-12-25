package com.xktime.model.pojo.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "请求注册dto")
public class RegisterDto {
    @ApiModelProperty("账户")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("性别  0为未知 1为男 2为女")
    private int sex;
    @ApiModelProperty("电话号码")
    private String phoneNumber;
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @ApiModelProperty("用户地区")
    private String region;
}
