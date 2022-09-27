package com.xktime.model.mongo.services;

import com.xktime.model.pojo.user.entity.AdminUser;
import com.xktime.model.services.IAdminUserDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class AdminUserDBService extends IAdminUserDBService {

    @Override
    public void saveUser(AdminUser user) {

    }

    @Override
    public AdminUser queryByAccount(String account) {
        return null;
    }
}
