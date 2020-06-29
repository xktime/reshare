package com.xktime.article.controller.admin;

import com.xktime.article.service.ArticleService;
import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.ArticleDto;
import com.xktime.model.article.dtos.AuditDto;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    CrawlerArticleService crawlerArticleService;

    @Autowired
    ArticleService articleService;

    @PostMapping("loadArticle")
    public List<ArticleDto> loadArticle(@RequestBody LoadArticleDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            dto.setLoadArticleType(ArticleTypeEnum.CRAWLER_ARTICLE.getDec());
        }
        if (dto.getSize() == 0) {
            dto.setSize(10);
        }
        if (dto.getPage() == 0) {
            dto.setPage(1);
        }
        dto.setPageStartIndex((dto.getPage() - 1) * dto.getSize());
        if (ArticleTypeEnum.CRAWLER_ARTICLE.getDec().equals(dto.getLoadArticleType())) {
            List<ArticleDto> articles = new ArrayList<>();
            List<CrawlerArticle> crawlerArticles = crawlerArticleService.load(dto);
            for (CrawlerArticle crawlerArticle : crawlerArticles) {
                ArticleDto article = new ArticleDto();
                BeanUtils.copyProperties(crawlerArticle, article);
                articles.add(article);
            }
            return articles;
        }
        return new ArrayList<>();
    }

    @RequestMapping("audit")
    public void audit(@RequestBody AuditDto dto) {
        crawlerArticleService.audit(dto);
    }
}
