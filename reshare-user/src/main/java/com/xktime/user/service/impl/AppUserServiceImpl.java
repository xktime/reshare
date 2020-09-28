package com.xktime.user.service.impl;

import com.xktime.model.mappers.user.AppUserMapper;
import com.xktime.model.user.pojos.AppUser;
import com.xktime.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AppUserService")
public class AppUserServiceImpl implements UserService<AppUser> {

    @Autowired
    AppUserMapper appUserMapper;

    @Override
    public void save(AppUser user) {
        appUserMapper.saveUser(user);
    }

    @Override
    public AppUser queryByAccount(String account) {
        return appUserMapper.queryByAccount(account);
    }
}
