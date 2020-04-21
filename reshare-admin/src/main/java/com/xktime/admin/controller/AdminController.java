package com.xktime.admin.controller;

import com.xktime.apis.admin.AdminControllerApi;
import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController implements AdminControllerApi {

    @Autowired
    CrawlerArticleService crawlerArticleService;

    @Override
    @GetMapping("crawlerArticle")
    public ResponseResult loadCrawlerArticle(ArticleHomeDto dto) {
        ResponseResult<List<CrawlerArticle>> result = new ResponseResult<>();
        result.ok(crawlerArticleService.load());
        return result;
    }
}
