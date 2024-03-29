package com.xktime.user.service.impl;


import com.xktime.model.pojo.user.entity.AdminUser;
import com.xktime.model.services.IAdminUserDBService;
import com.xktime.user.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBaseUserServiceImpl implements BaseUserService<AdminUser> {

    @Autowired
    IAdminUserDBService adminUserMapper;

    @Override
    public void save(AdminUser user) {
        adminUserMapper.saveUser(user);
    }

    @Override
    public AdminUser queryByAccount(String account) {
        return adminUserMapper.queryByAccount(account);
    }

    @Override
    public AdminUser queryByUserId(long userId) {
        return null;
    }

    @Override
    public String getAccountByToken(String token) {
        return null;
    }
}
