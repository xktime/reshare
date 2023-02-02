package com.xktime.article.service;

import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.entity.BaseVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.type.ArticleStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BaseAuditable {

    //todo 太丑陋了 重构
    default void verify(VerifiedArticleServiceImpl verifiedArticleService, VerifyDto dto) {
        BaseArticleService service = ArticleTypeEnum.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        BaseVerifyArticle verifyArticle = (BaseVerifyArticle) service.findById(dto.getArticleId());
        if (verifyArticle == null) {
            throw new NullPointerException("文章为空");
        }
        VerifyQuery verifyQuery = dto.toQuery();
        if (dto.getStatus() == ArticleStatusEnum.PASSED.getStatus()) {
            //如果是通过审核,插入数据库
            VerifiedArticle verifiedArticle = verifyArticle.toArticle();
            verifiedArticleService.save(verifiedArticle);
            verifyQuery.setBindId(verifiedArticle.getId());
        } else if (dto.getStatus() == ArticleStatusEnum.UNPASSED.getStatus()) {
            //如果是不通过,从数据库删除
            verifiedArticleService.removeById(verifyArticle.getBindId());
            verifyQuery.setBindId(0);
        }
        ((BaseAuditable) service).modifyState(verifyQuery);
    }

    void modifyState(VerifyQuery verifyQuery);

    List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery);
}
