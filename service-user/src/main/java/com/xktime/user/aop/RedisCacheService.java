package com.xktime.user.aop;

import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.user.entity.BaseUser;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisKeyUtil;
import com.xktime.model.redis.RedisUtil;
import com.xktime.utils.util.CodeUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisCacheService {

    @Autowired
    RedisUtil redisUtil;

    //todo 缓存先屏蔽了
//    @AfterReturning(returning = "user", pointcut = "execution(public * com.xktime.user.service..*.query*(..))")
    public void after(BaseUser user) {
        if (user == null) {
            return;
        }
        String token = CodeUtil.encryptBase64(user.getAccount(), CodeConstant.LOGIN_TOKEN_BASE64_KEY);
        String userKey = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, user.getUserId());
        String tokenKey = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token);
        if (redisUtil.mapExists(RedisCommonKey.APP_USR, userKey)
                && redisUtil.mapExists(RedisCommonKey.APP_USR, tokenKey)) {
            return;
        }
        redisUtil.mapSet(RedisCommonKey.APP_USR, userKey, user);
        redisUtil.mapSet(RedisCommonKey.APP_USR, tokenKey, user);
    }
}
