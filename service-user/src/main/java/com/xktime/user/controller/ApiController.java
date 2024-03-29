package com.xktime.user.controller;

import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    AppBaseUserServiceImpl userService;

    @PostMapping("getUserByToken")
    public AppUser getUserByToken(String token) {
        String account = userService.getAccountByToken(token);
        return getUserByAccount(account);
    }

    @PostMapping("getUserByAccount")
    public AppUser getUserByAccount(String account) {
        return userService.queryByAccount(account);
    }

    @PostMapping("getUserByUserId")
    public AppUser getUserByUserId(@RequestBody long userId) {
        return userService.queryByUserId(userId);
    }
}
