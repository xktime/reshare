package com.xktime.user.controller;

import com.xktime.model.article.dtos.LoginDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.CodeConstants;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AdminUser;
import com.xktime.user.service.UserService;
import com.xktime.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseResult login(@RequestBody LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        if (dto == null || dto.getUserName() == null || dto.getPassword() == null) {
            return responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
        }
        AdminUser adminUser = userService.queryAdminUserByAccount(dto.getUserName());
        if (adminUser == null) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getCode(), HttpCodeEnum.LOGIN_FAIL_ACCOUNT.getErrorMessage());
        }
        String password = adminUser.getPassword();
        String encryptedPassWord = CodeUtil.encryptBase64(dto.getPassword(), CodeConstants.LOGIN_BASE64_KEY);
        if (!password.equals(encryptedPassWord)) {
            return responseResult.error(HttpCodeEnum.LOGIN_FAIL_PASSWORD.getCode(), HttpCodeEnum.LOGIN_FAIL_PASSWORD.getErrorMessage());
        }
        return responseResult;
    }

}
