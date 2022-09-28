package com.xktime.model.pojo.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.utils.util.CodeUtil;
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

    public AppUser toUser(long id) {
        AppUser user = new AppUser();
        user.setAccount(account);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setRegion(region);
        user.setSex(sex);
        String encryptedPassword = CodeUtil.encryptBase64(password, CodeConstant.LOGIN_PASSWORD_BASE64_KEY);
        user.setPassword(encryptedPassword);
        user.setCreateTime(new Date());
        user.setUserName(account);
        user.setUserId(id);
        user.setProfile("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        return user;
    }
}
