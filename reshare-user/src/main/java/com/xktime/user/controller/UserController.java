package com.xktime.user.controller;

import com.xktime.model.account.dtos.RegisterDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.CodeConstants;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pos.AppUser;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import com.xktime.utils.CodeUtil;
import com.xktime.utils.SnowflakeIdUtil;
import com.xktime.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AppBaseUserServiceImpl appUserService;

    @Autowired
    SnowflakeIdUtil snowflakeId;

    @RequestMapping("register")
    public ResponseResult register(@RequestBody RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        String account = dto.getAccount();
        if (appUserService.queryByAccount(account) != null) {
            result.error(HttpCodeEnum.ACCOUNT_EXISTS.getCode(), HttpCodeEnum.ACCOUNT_EXISTS.getErrorMessage());
            return result;
        }
        AppUser user = new AppUser();
        BeanUtils.copyProperties(dto, user);
        String password = CodeUtil.encryptBase64(dto.getPassword(), CodeConstants.LOGIN_PASSWORD_BASE64_KEY);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUserName(dto.getAccount());
        UUID uuid = UUID.randomUUID();
        user.setUserId(UUIDUtil.toBytes(uuid));
        user.setImage("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        appUserService.save(user);
        return result.ok(CodeUtil.encryptBase64(dto.getAccount(), CodeConstants.LOGIN_TOKEN_BASE64_KEY));
    }
}
