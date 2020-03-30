package com.xktime.model;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.mappers.article.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareModelApplication.class)
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void testArticle() {
        ArticleHomeDto dto = new ArticleHomeDto();
        System.out.println(articleMapper.loadArticleListByLocation(dto));
    }
}
