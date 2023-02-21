package com.xktime.article;

import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.model.pojo.article.query.LoadQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
public class VerifiedArticleTest {

    @Autowired
    VerifiedArticleServiceImpl verifiedArticle;

    @Test
    public void testArticle() {
        LoadQuery loadQuery = new LoadQuery();
        loadQuery.setSize(10);
        loadQuery.setPage(0);
        loadQuery.setLastTime(1622096628000l);
        System.out.println(verifiedArticle.loadSimpleArticles(loadQuery).toString());
    }

}
