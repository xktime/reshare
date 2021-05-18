package com.xktime.article.aop;

import com.xktime.model.pojo.article.entity.Article;
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
        if (obj == null) {
            return;
        }
        //todo 不够优雅，需要重构
        if (obj instanceof Article) {
            Article article = (Article)obj;
            if (redisUtil.mapExists(RedisCommonKey.COMMON_ARTICLE, article.getId())) {
                return;
            }
            redisUtil.mapSet(RedisCommonKey.COMMON_ARTICLE, article.getId(), article);
        } else if (obj instanceof CrawlerVerifyArticle) {
            CrawlerVerifyArticle article = (CrawlerVerifyArticle)obj;
            if (redisUtil.mapExists(RedisCommonKey.CRAWLER_ARTICLE, article.getId())) {
                return;
            }
            redisUtil.mapSet(RedisCommonKey.CRAWLER_ARTICLE, article.getId(), article);
        } else if (obj instanceof OriginalVerifyArticle) {
            OriginalVerifyArticle article = (OriginalVerifyArticle)obj;
            if (redisUtil.mapExists(RedisCommonKey.ORIGINAL_ARTICLE, article.getId())) {
                return;
            }
            redisUtil.mapSet(RedisCommonKey.ORIGINAL_ARTICLE, article.getId(), article);
        }
    }
}
