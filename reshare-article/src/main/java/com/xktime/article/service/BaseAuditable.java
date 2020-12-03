package com.xktime.article.service;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dos.VerifyDo;
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

    //todo 添加事务支持
    public void verify(VerifyDto dto) {
        BaseArticleService service = factory.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        BaseVerifyArticle verifyArticle = (BaseVerifyArticle) service.findById(dto.getArticleId());
        if (verifyArticle == null) {
            throw new NullPointerException("文章为空");
        }
        VerifyDo verifyDo = new VerifyDo();
        BeanUtils.copyProperties(dto, verifyDo);
        if (dto.getStatus() == 2) {
            //如果是通过审核插入数据库
            Article article = new Article();
            BeanUtils.copyProperties(verifyArticle, article);
            articleService.save(article);
            verifyDo.setBindId(article.getId());
        } else if (dto.getStatus() == 1) {
            //如果是不通过从数据库删除
            articleService.removeById(verifyArticle.getBindId());
            verifyDo.setBindId(0);
        }
        ((BaseAuditable) service).modifyState(verifyDo);
    }

    public abstract void modifyState(VerifyDo verifyDo);
}
