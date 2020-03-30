package com.xktime.model;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.mappers.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.xktime.common.mysql.core")
@RestController
public class ReshareModelApplication {
    @Autowired
    ArticleMapper articleMapper;
    public static void main(String[] args) {
        SpringApplication.run(ReshareModelApplication.class, args);
    }

    @GetMapping("hello")
    public String hello() {
        return articleMapper.loadArticleListByLocation(new ArticleHomeDto()).toString();
    }

}
