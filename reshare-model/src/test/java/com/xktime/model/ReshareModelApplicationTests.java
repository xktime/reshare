package com.xktime.article;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.mappers.article.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ReshareArticleApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        System.out.println(dataSource);
    }

}
