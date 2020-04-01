package com.xktime.article;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.enums.UserStatusEnum;
import com.xktime.model.mappers.article.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareArticleApplication.class)
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    ArticleService articleService;

    @Test
    public void testArticle() {
        ArticleHomeDto dto = new ArticleHomeDto();
        System.out.println(articleService.load(dto, UserStatusEnum.LOGGED));
    }
}
