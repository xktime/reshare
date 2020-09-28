package com.xktime.user.controller;

import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import com.xktime.user.service.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    AppUserServiceImpl userService;

    @PostMapping("getUserByToken")
    public ResponseResult<AppUser> adminLogin(@RequestBody String token) {
        ResponseResult<AppUser> result = new ResponseResult<>();
        String account = userService.getAccountByToken(token);
        AppUser appUser = userService.queryByAccount(account);
        if (appUser == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT.getCode(), HttpCodeEnum.NOT_FIND_ACCOUNT.getErrorMessage());
        }
        return result.ok(appUser);
    }
}
