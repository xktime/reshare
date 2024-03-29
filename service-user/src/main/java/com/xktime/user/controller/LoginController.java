package com.xktime.user.controller;

import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AdminUser;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.user.service.impl.AdminBaseUserServiceImpl;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import com.xktime.utils.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    AdminBaseUserServiceImpl adminUserService;

    @Autowired
    AppBaseUserServiceImpl appUserService;

    @PostMapping("admin")
    public ResponseResult adminLogin(@RequestBody LoginDto dto) {
        ResponseResult result = new ResponseResult();
        if (dto == null || dto.getAccount() == null || dto.getPassword() == null) {
            return result.error(HttpCodeEnum.FAIL);
        }
        AdminUser adminUser = adminUserService.queryByAccount(dto.getAccount());
        if (adminUser == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT);
        }
        String password = adminUser.getPassword();
        String encryptedPassWord = CodeUtil.encryptBase64(dto.getPassword(), CodeConstant.LOGIN_PASSWORD_BASE64_KEY);
        if (!password.equals(encryptedPassWord)) {
            return result.error(HttpCodeEnum.LOGIN_FAIL_PASSWORD);
        }
        return result.ok(adminUserService.getTokenByAccount(dto.getAccount()));
    }

    @PostMapping("app")
    public ResponseResult appLogin(@RequestBody LoginDto dto) {
        ResponseResult result = new ResponseResult();
        if (dto == null || dto.getAccount() == null || dto.getPassword() == null) {
            return result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
        }
        AppUser user = appUserService.queryByAccount(dto.getAccount());
        if (user == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT);
        }
        String password = user.getPassword();
        String encryptedPassWord = CodeUtil.encryptBase64(dto.getPassword(), CodeConstant.LOGIN_PASSWORD_BASE64_KEY);
        if (!password.equals(encryptedPassWord)) {
            return result.error(HttpCodeEnum.LOGIN_FAIL_PASSWORD);
        }
        return result.ok(appUserService.getTokenByAccount(dto.getAccount()));
    }

}
