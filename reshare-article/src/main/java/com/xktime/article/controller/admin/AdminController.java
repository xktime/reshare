package com.xktime.article.controller.admin;

import com.xktime.article.service.ArticleService;
import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.dtos.AuditDto;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.common.dtos.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    CrawlerArticleService crawlerArticleService;

    @Autowired
    ArticleService articleService;

    @PostMapping("loadArticle")
    public ResponseResult loadArticle(@RequestBody LoadArticleDto dto) {
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
            ResponseResult<List<CrawlerArticle>> responseResult = new ResponseResult<>();
            return responseResult.ok(crawlerArticleService.load(dto));
        }
        return new ResponseResult();
    }

    @RequestMapping("audit")
    public void audit(@RequestBody AuditDto dto) {
        crawlerArticleService.audit(dto);
    }
}
