package com.xktime.article.controller;

import com.xktime.apis.article.ArticleHomeControllerApi;
import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleHomeController implements ArticleHomeControllerApi {

    @Autowired
    ArticleService articleService;

    @Override
    @GetMapping("load")
    public ResponseResult load(ArticleHomeDto dto) {
        return null;
    }
}