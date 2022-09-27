package com.xktime.model.mysql.services;

import com.xktime.model.mysql.mappers.user.AdminUserMapper;
import com.xktime.model.pojo.user.entity.AdminUser;
import com.xktime.model.services.IAdminUserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class AdminUserDBService extends IAdminUserDBService {

    @Autowired
    AdminUserMapper adminUserDBService;

    @Override
    public void saveUser(AdminUser user) {
        adminUserDBService.saveUser(user);
    }

    @Override
    public AdminUser queryByAccount(String account) {
        return adminUserDBService.queryByAccount(account);
    }
}
