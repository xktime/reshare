package com.xktime.article.aop;

import com.xktime.model.pojo.article.entity.BaseArticle;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
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

    @Before("execution(public * com.xktime.article.service..*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("-----------------------------before---------------------------");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg.toString());
        }
    }

    @AfterReturning(returning = "obj", pointcut = "execution(public * com.xktime.article.service..*.find*(..))")
    public void after(Object obj) {
        if (!(obj instanceof BaseArticle)) {
            return;
        }
        BaseArticle baseArticle = (BaseArticle)obj;
        RedisCommonKey redisKey = getRedisKey(baseArticle);
        if (redisKey == null) {
            return;
        }
        if (redisUtil.mapExists(redisKey, baseArticle.getId())) {
            return;
        }
        redisUtil.mapSet(redisKey, baseArticle.getId(), baseArticle);
    }

    private RedisCommonKey getRedisKey(BaseArticle article) {
        if (article instanceof VerifiedArticle) {
            return RedisCommonKey.COMMON_ARTICLE;
        } else if (article instanceof CrawlerVerifyArticle) {
            return RedisCommonKey.CRAWLER_ARTICLE;
        } else if (article instanceof OriginalVerifyArticle) {
            return RedisCommonKey.ORIGINAL_ARTICLE;
        }
        return null;
    }
}
