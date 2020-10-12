package com.xktime.user.controller;

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
    public AppUser adminLogin(@RequestBody String token) {
        String account = userService.getAccountByToken(token);
        AppUser appUser = userService.queryByAccount(account);
        return appUser;
    }
}
