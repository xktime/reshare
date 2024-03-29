package com.xktime.model.redis.cache;

import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class RedisCacheService {

    @Autowired
    RedisUtil redisUtil;

    //todo 要做更新数据时更新缓存
    @Around("@annotation(com.xktime.model.redis.cache.RedisCache)")
    public Object around(ProceedingJoinPoint point) {
        var args = point.getArgs();
        long id = (long) args[0];
        var redisKey = getRedisKey(point);
        if (redisKey == null) {
            try {
                return point.proceed();
            } catch (Throwable e) {
                return null;
            }
        }
        if (!redisUtil.mapExists(redisKey, id)) {
            try {
                var o = point.proceed();
                redisUtil.mapSet(redisKey, id, o);
                return o;
            } catch (Throwable e) {
                return null;
            }
        } else {
            return redisUtil.mapGet(redisKey, id);
        }
    }

    public RedisCommonKey getRedisKey(ProceedingJoinPoint point) {
        var signature = (MethodSignature) point.getSignature();
        var target = point.getTarget();
        try {
            var realMethod = target.getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            var redisCache = AnnotationUtils.findAnnotation(realMethod, RedisCache.class);
            if (Objects.nonNull(redisCache)) {
                return redisCache.key();
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
        return null;
    }

}
