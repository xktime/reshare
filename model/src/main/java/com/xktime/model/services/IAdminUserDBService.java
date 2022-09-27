package com.xktime.model.services;

import com.xktime.model.pojo.user.entity.AdminUser;

public abstract class IAdminUserDBService {
    abstract public void saveUser(AdminUser user);

    abstract public AdminUser queryByAccount(String account);
}
