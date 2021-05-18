package com.xktime.article;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    ArticleServiceImpl articleService;

    @Autowired
    CrawlerArticleServiceImpl crawlerArticleService;

    @Autowired
    OriginalArticleServiceImpl originalArticleServiceImpl;

    @org.junit.Test
    public void testAop() {
        articleService.findById(19201l);
        crawlerArticleService.findById(19201l);
    }
}
