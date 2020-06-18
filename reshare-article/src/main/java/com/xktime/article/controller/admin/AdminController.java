package com.xktime.article.controller.admin;

import com.xktime.article.service.ArticleService;
import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.AuditDto;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController{

    @Autowired
    CrawlerArticleService crawlerArticleService;

    @Autowired
    ArticleService articleService;

    @PostMapping("crawlerArticle")
    public List<CrawlerArticle> loadCrawlerArticle(@RequestBody LoadArticleDto dto) {
        return crawlerArticleService.load();
    }

    @RequestMapping("audit")
    public void audit(@RequestBody AuditDto dto) {
        crawlerArticleService.audit(dto.getArticleId(), dto.getStatus());
    }
}
