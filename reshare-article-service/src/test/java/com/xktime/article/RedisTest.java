package com.xktime.article;

import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    VerifiedArticleServiceImpl verifiedArticleService;

    @Autowired
    CrawlerArticleServiceImpl crawlerArticleService;

    @Autowired
    OriginalArticleServiceImpl originalArticleServiceImpl;

    @Autowired
    RedisUtil redisUtil;

    @org.junit.Test
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
