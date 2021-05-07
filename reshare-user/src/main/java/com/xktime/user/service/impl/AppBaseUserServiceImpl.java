package com.xktime.user.service.impl;

import com.xktime.model.mappers.user.AppUserMapper;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.user.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppBaseUserServiceImpl implements BaseUserService<AppUser> {

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

    @Override
    public AppUser queryByUserId(long userId) {
        return appUserMapper.queryByUserId(userId);
    }
}
