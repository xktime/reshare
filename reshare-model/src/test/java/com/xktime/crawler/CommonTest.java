package com.xktime.crawler;

import com.xktime.model.ReshareModelApplication;
import com.xktime.model.article.pos.Article;
import com.xktime.model.mappers.article.ArticleMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(classes = ReshareModelApplication.class)
@RunWith(SpringRunner.class)
public class CommonTest {

    @Autowired
    ArticleMapper articleMapper;

    @org.junit.Test
    public void test() {
        Article article = new Article();
        article.setAuthorId(553787);
        article.setContent("嘻嘻哈哈");
        article.setPublishTime(new Date());
        articleMapper.saveArticle(article);
    }
}
