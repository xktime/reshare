package com.xktime.user.controller;

import com.xktime.model.account.dtos.RegisterDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.CodeConstants;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import com.xktime.user.service.UserService;
import com.xktime.utils.CodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("register")
    public ResponseResult register(@RequestBody RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        String account = dto.getAccount();
        AppUser appUser = userService.queryAppUserByAccount(account);
        if (appUser != null) {
            result.error(HttpCodeEnum.ACCOUNT_EXISTS.getCode(), HttpCodeEnum.ACCOUNT_EXISTS.getErrorMessage());
            return result;
        }
        AppUser user = new AppUser();
        BeanUtils.copyProperties(user, dto);
        String password = CodeUtil.encryptBase64(dto.getPassword(), CodeConstants.LOGIN_PASSWORD_BASE64_KEY);
        user.setPassword(password);
        userService.saveAppUser(user);
        return result.ok(CodeUtil.encryptBase64(dto.getAccount(), CodeConstants.LOGIN_TOKEN_BASE64_KEY));
    }
}
