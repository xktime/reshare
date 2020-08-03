package com.xktime.user.service.impl;

import com.xktime.model.common.enums.CodeConstants;
import com.xktime.model.mappers.user.AdminUserMapper;
import com.xktime.model.user.pojos.AdminUser;
import com.xktime.user.service.UserService;
import com.xktime.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AdminUserMapper adminUserMapper;

    public static final String TOKEN_BASE64_KEY = CodeConstants.LOGIN_TOKEN_BASE64_KEY;


    @Override
    public void saveAdminUser(AdminUser user) {
        adminUserMapper.saveUser(user);
    }

    @Override
    public AdminUser queryAdminUserByAccount(String account) {
        return adminUserMapper.queryByAccount(account);
    }

    @Override
    public String getAccountByToken(String token) {
        return CodeUtil.decryptBase64(token, TOKEN_BASE64_KEY);
    }

    @Override
    public String getTokenByAccount(String account) {
        return CodeUtil.encryptBase64(account, TOKEN_BASE64_KEY);
    }
}
