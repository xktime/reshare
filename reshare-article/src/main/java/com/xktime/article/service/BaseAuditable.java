package com.xktime.article.service;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.article.pojos.BaseVerifyArticle;
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
        BaseVerifyArticle verifyArticle = (BaseVerifyArticle) service.findById(dto.getArticleId());
        if (verifyArticle == null) {
            throw new NullPointerException("文章为空");
        }
        if (dto.getStatus() == 2) {
            //如果是通过审核插入数据库
            Article article = new Article();
            BeanUtils.copyProperties(verifyArticle, article);
            long bindId = articleService.save(article);
            dto.setBindId(bindId);
        } else if (dto.getStatus() == 1) {
            //如果是不通过从数据库删除
            //todo article的id与审核文章的id不一定相同
            articleService.deleteById(verifyArticle.getBindId());
            dto.setBindId(0);
        }
        ((BaseAuditable) service).modifyState(dto);
    }

    public abstract void modifyState(VerifyDto dto);
}
