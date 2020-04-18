package com.xktime.article;

import com.xktime.article.service.ArticleService;
import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.enums.UserStatusEnum;
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
        System.out.println(articleService1.findByUrl("aa"));
    }
}
