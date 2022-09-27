package com.xktime.model.mongo.services;

import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.services.IAppUserDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class AppUserDBService extends IAppUserDBService {

    @Override
    public void saveUser(AppUser user) {

    }

    @Override
    public AppUser queryByAccount(String account) {
        return null;
    }

    @Override
    public AppUser queryByUserId(long userId) {
        return null;
    }
}
