package com.xktime.user.aop;

import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.utils.CodeUtil;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
import com.xktime.utils.common.RedisKeyUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisCacheService {

    @Autowired
    RedisUtil redisUtil;

    @Before("execution(public * com.xktime.user.service..*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("-----------------------------before---------------------------");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg.toString());
        }
    }

    @AfterReturning(returning = "user", pointcut = "execution(public * com.xktime.user.service..*.query*(..))")
    public void after(AppUser user) {
        if (user == null) {
            return;
        }
        String token = CodeUtil.encryptBase64(user.getAccount(), CodeConstant.LOGIN_TOKEN_BASE64_KEY);
        String userKey = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, user.getUserId());
        String tokenKey = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token);
        if (redisUtil.hmExists(RedisCommonKey.APP_USR, userKey)
                && redisUtil.hmExists(RedisCommonKey.APP_USR, tokenKey)) {
            return;
        }
        redisUtil.hmSet(RedisCommonKey.APP_USR, userKey, user);
        redisUtil.hmSet(RedisCommonKey.APP_USR, tokenKey, user);
    }
}
