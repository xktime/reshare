package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    CrawlerArticleServiceImpl articleService;

    @Autowired
    VerifiedArticleServiceImpl verifiedArticleService;

    @RequestMapping("verify")
    public ResponseResult audit(VerifyDto dto) {
        BaseArticleService service = ArticleTypeEnum.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        ((BaseAuditable) service).verify(verifiedArticleService, dto);
        return ResponseResult.okResult(HttpCodeEnum.SUCCESS);
    }
}
