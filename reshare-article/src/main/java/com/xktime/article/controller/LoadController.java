package com.xktime.article.controller;

import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
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
    ArticleServiceFactory factory;

    @PostMapping("byType")
    public List<VerifyArticleDto> byType(@RequestBody LoadArticleDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            throw new NullPointerException("LoadArticleType为空");
        }
        if (dto.getSize() <= 0) {
            dto.setSize(10);
        }
        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }
        return factory.getService(dto.getLoadArticleType()).loadVerifyArticleDtoList(dto);
    }

    @PostMapping("byAccount")
    public List<VerifyArticleDto> byAccount(@RequestBody LoadArticleDto dto) {
        if (dto.getUser() == null) {
            return byType(dto);
        }
        if (dto.getSize() <= 0) {
            dto.setSize(10);
        }
        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }
        return factory.getService(dto.getLoadArticleType()).loadVerifyArticleDtoList(dto);
    }
}
