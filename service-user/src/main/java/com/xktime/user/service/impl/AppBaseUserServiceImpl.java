package com.xktime.user.service.impl;

import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisKeyUtil;
import com.xktime.model.redis.RedisUtil;
import com.xktime.model.services.IAppUserDBService;
import com.xktime.user.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppBaseUserServiceImpl implements BaseUserService<AppUser> {

    @Autowired
    IAppUserDBService iAppUserDBService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(AppUser user) {
        iAppUserDBService.saveUser(user);
    }

    @Override
    public AppUser queryByAccount(String account) {
        String token = getTokenByAccount(account);
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token));
        return user != null ? user : iAppUserDBService.queryByAccount(account);
    }

    @Override
    public AppUser queryByUserId(long userId) {
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, userId));
        return user != null ? user : iAppUserDBService.queryByUserId(userId);
    }
}
