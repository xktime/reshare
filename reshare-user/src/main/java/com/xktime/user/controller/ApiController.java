package com.xktime.user.controller;

import com.xktime.model.user.pojos.AppUser;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    AppBaseUserServiceImpl userService;

    @PostMapping("getUserByToken")
    public AppUser adminLogin(@RequestBody String token) {
        String account = userService.getAccountByToken(token);
        AppUser appUser = userService.queryByAccount(account);
        return appUser;
    }
}
