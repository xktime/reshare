package com.xktime.user.service.impl;

import com.xktime.model.mappers.user.AdminUserMapper;
import com.xktime.model.user.pojos.AdminUser;
import com.xktime.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AdminUserMapper adminUserMapper;


    @Override
    public void saveAdminUser(AdminUser user) {
        adminUserMapper.saveUser(user);
    }

    @Override
    public AdminUser queryAdminUserByAccount(String account) {
        return adminUserMapper.queryByAccount(account);
    }
}
