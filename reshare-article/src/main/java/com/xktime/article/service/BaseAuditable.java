package com.xktime.article.service;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.article.entity.BaseVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.type.ArticleStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//todo 改用AOP事务支持
public abstract class BaseAuditable {

    @Autowired
    ArticleServiceImpl articleService;

    public void verify(VerifyDto dto) {
        BaseArticleService service = ArticleTypeEnum.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        BaseVerifyArticle verifyArticle = (BaseVerifyArticle) service.findById(dto.getArticleId());
        if (verifyArticle == null) {
            throw new NullPointerException("文章为空");
        }
        VerifyQuery verifyQuery = dto.toQuery();
        //todo 可以将操作整合到Transfer内
        if (dto.getStatus() == ArticleStatusEnum.PASSED.getStatus()) {
            //如果是通过审核,插入数据库
            Article article = verifyArticle.toArticle();
            articleService.save(article);
            verifyQuery.setBindId(article.getId());
        } else if (dto.getStatus() == ArticleStatusEnum.UNPASSED.getStatus()) {
            //如果是不通过,从数据库删除
            articleService.removeById(verifyArticle.getBindId());
            verifyQuery.setBindId(0);
        }
        ((BaseAuditable) service).modifyState(verifyQuery);
    }

    public abstract void modifyState(VerifyQuery verifyQuery);

    public abstract List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery);
}
