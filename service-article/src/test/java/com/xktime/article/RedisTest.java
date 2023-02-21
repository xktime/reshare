package com.xktime.article;

import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ReshareArticleApplication.class)
public class RedisTest {

    @Autowired
    VerifiedArticleServiceImpl verifiedArticleService;

    @Autowired
    CrawlerArticleServiceImpl crawlerArticleService;

    @Autowired
    OriginalArticleServiceImpl originalArticleServiceImpl;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testAop() {
        long articleId = 19206;
        boolean isExists= redisUtil.mapExists(RedisCommonKey.COMMON_ARTICLE,articleId);
        System.out.println("获取前isExists:" + isExists);
        System.out.println(verifiedArticleService.findById(articleId));
        isExists= redisUtil.mapExists(RedisCommonKey.COMMON_ARTICLE,articleId);
        System.out.println("获取后isExists:" + isExists);
        System.out.println(verifiedArticleService.findById(articleId));
    }
}
