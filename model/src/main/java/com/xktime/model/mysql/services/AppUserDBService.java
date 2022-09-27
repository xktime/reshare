package com.xktime.model.mysql.services;

import com.xktime.model.mysql.mappers.user.AppUserMapper;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.services.IAppUserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class AppUserDBService extends IAppUserDBService {

    @Autowired
    AppUserMapper appUserMapper;

    @Override
    public void saveUser(AppUser user) {
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
