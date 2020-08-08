package com.xktime.article.controller;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("load")
public class LoadController {

    @Autowired
    ArticleService articleService;

    @PostMapping("loadVerifyArticle")
    public List<VerifyArticleDto> loadArticle(@RequestBody LoadArticleDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            dto.setLoadArticleType(ArticleTypeEnum.CRAWLER_ARTICLE.getDec());
        }
        if (dto.getSize() <= 0) {
            dto.setSize(10);
        }
        if (dto.getPage() == 0) {
            dto.setPage(1);
        }
        return articleService.loadVerifyArticleDtoList(dto);
    }
}