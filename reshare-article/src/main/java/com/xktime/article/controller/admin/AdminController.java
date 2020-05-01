package com.xktime.article.controller.admin;

import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController{

    @Autowired
    CrawlerArticleService crawlerArticleService;

    @PostMapping("crawlerArticle")
    public List<CrawlerArticle> loadCrawlerArticle(@RequestBody ArticleHomeDto dto) {
        return crawlerArticleService.load();
    }
}
