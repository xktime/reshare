package com.xktime.article.service;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseAuditable {

    @Autowired
    ArticleServiceImpl articleService;

    @Autowired
    ArticleServiceFactory factory;

    //todo 插入article数据库
    public void verify(VerifyDto dto) {
        BaseArticleService service = factory.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        Article article = new Article();
        BeanUtils.copyProperties(article, service.findById(dto.getArticleId()));
        articleService.save(article);
        ((BaseAuditable) service).modifyState(dto);
    }

    public abstract void modifyState(VerifyDto dto);
}
