package com.xktime.model.redis.cache;

import com.xktime.model.pojo.article.entity.BaseArticle;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Objects;

@Aspect
@Component
public class RedisCacheService {

    @Autowired
    RedisUtil redisUtil;

    @Around("@annotation(com.xktime.model.redis.cache.RedisCache)")
    public Object around(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        int id = (int)args[0];
        RedisCommonKey redisKey = getRedisKey(point);
        if (redisUtil.mapExists(redisKey, id)) {
            return redisUtil.mapGet(redisKey, id);
        }
        try {
            Object o = point.proceed();
            redisUtil.mapSet(redisKey, id, o);
            return o;
        } catch (Throwable e) {
        }
        return null;
    }

    public RedisCommonKey getRedisKey(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        RedisCache redisCache = AnnotationUtils.findAnnotation(signature.getMethod(), RedisCache.class);
        if (Objects.nonNull(redisCache)) {
            return redisCache.key();
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), RedisCache.class).key();
    }

}
