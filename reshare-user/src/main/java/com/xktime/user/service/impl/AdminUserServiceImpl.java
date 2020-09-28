package com.xktime.user.service.impl;


import com.xktime.model.mappers.user.AdminUserMapper;
import com.xktime.model.user.pojos.AdminUser;
import com.xktime.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements UserService<AdminUser> {

    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public void save(AdminUser user) {
        adminUserMapper.saveUser(user);
    }

    @Override
    public AdminUser queryByAccount(String account) {
        return adminUserMapper.queryByAccount(account);
    }
}
