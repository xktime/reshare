package com.xktime.article;

import com.xktime.article.type.ArticleTypeEnum;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {

    @org.junit.Test
    public void testArticle() {
        System.out.println(ArticleTypeEnum.CRAWLER_ARTICLE.getService().findById(1));
    }
}
