package com.xktime.user.controller;

import com.xktime.model.account.dtos.LoginDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.CodeConstants;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AdminUser;
import com.xktime.model.user.pojos.AppUser;
import com.xktime.user.service.UserService;
import com.xktime.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("admin")
    public ResponseResult adminLogin(@RequestBody LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        if (dto == null || dto.getAccount() == null || dto.getPassword() == null) {
            return responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
        }
        AdminUser adminUser = userService.queryAdminUserByAccount(dto.getAccount());
        if (adminUser == null) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getCode(), HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getErrorMessage());
        }
        String password = adminUser.getPassword();
        String encryptedPassWord = CodeUtil.encryptBase64(dto.getPassword(), CodeConstants.LOGIN_PASSWORD_BASE64_KEY);
        if (!password.equals(encryptedPassWord)) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_PASSWORD.getCode(), HttpCodeEnum.LOGIN_FAIL_PASSWORD.getErrorMessage());
        }
        return responseResult.ok(userService.getTokenByAccount(dto.getAccount()));
    }

    @PostMapping("app")
    public ResponseResult appLogin(@RequestBody LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        if (dto == null || dto.getAccount() == null || dto.getPassword() == null) {
            return responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
        }
        AppUser user = userService.queryAppUserByAccount(dto.getAccount());
        if (user == null) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getCode(), HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getErrorMessage());
        }
        String password = user.getPassword();
        String encryptedPassWord = CodeUtil.encryptBase64(dto.getPassword(), CodeConstants.LOGIN_PASSWORD_BASE64_KEY);
        if (!password.equals(encryptedPassWord)) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_PASSWORD.getCode(), HttpCodeEnum.LOGIN_FAIL_PASSWORD.getErrorMessage());
        }
        return responseResult.ok(userService.getTokenByAccount(dto.getAccount()));
    }

}
