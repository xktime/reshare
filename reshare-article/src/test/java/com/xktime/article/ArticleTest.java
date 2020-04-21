package com.xktime.article;

import com.xktime.article.service.CrawlerArticleService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Autowired
    CrawlerArticleService articleService1;

    @org.junit.Test
    public void testArticle() {
    }
}
