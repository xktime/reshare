package com.xktime.user.service.impl;

import com.xktime.model.mappers.user.AppUserMapper;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.user.service.BaseUserService;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
import com.xktime.utils.common.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppBaseUserServiceImpl implements BaseUserService<AppUser> {

    @Autowired
    AppUserMapper appUserMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(AppUser user) {
        appUserMapper.saveUser(user);
    }

    @Override
    public AppUser queryByAccount(String account) {
        String token = getTokenByAccount(account);
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token));
        return user != null ? user : appUserMapper.queryByAccount(account);
    }

    @Override
    public AppUser queryByUserId(long userId) {
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, userId));
        return user != null ? user : appUserMapper.queryByUserId(userId);
    }
}
