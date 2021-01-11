package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    CrawlerArticleServiceImpl articleService;

    @Autowired
    ArticleServiceFactory factory;

    @RequestMapping("verify")
    public void audit(@RequestBody VerifyDto dto) {
        BaseArticleService service = factory.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        ((BaseAuditable) service).verify(dto);
    }
}
