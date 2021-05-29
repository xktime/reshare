package com.xktime.article.aop;

import com.xktime.model.pojo.article.entity.BaseArticle;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisCacheService {

    @Autowired
    RedisUtil redisUtil;

    @AfterReturning(returning = "article", pointcut = "execution(public * com.xktime.article.service..*.find*(..))")
    public void after(BaseArticle article) {
        RedisCommonKey redisKey = getRedisKey(article);
        if (redisKey == null) {
            return;
        }
        if (redisUtil.mapExists(redisKey, article.getId())) {
            return;
        }
        redisUtil.mapSet(redisKey, article.getId(), article);
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
