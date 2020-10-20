package com.xktime.article.controller;

import com.xktime.article.service.impl.CrawlerBaseArticleServiceImpl;
import com.xktime.model.article.dtos.VerifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    CrawlerBaseArticleServiceImpl articleService;

    @RequestMapping("verify")
    public void audit(@RequestBody VerifyDto dto) {
        articleService.verify(dto);
    }
}
