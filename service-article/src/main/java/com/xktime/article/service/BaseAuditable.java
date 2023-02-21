package com.xktime.article.service;

import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.BaseVerifyArticle;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.type.ArticleStatusEnum;
import java.util.List;
public interface BaseAuditable {

    //todo 太丑陋了 重构
//    @Transactional(rollbackFor = Exception.class)
    default void verify(VerifiedArticleServiceImpl verifiedArticleService, VerifyDto dto) {
        BaseArticleService<BaseVerifyArticle> service = ArticleTypeEnum.getService(dto.getType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getType());
        }
        BaseVerifyArticle verifyArticle = service.findById(dto.getArticleId());
        if (verifyArticle == null) {
            throw new NullPointerException("文章为空");
        }
        if (verifyArticle.getStatus() == dto.getStatus()){//状态未改变直接返回
            return;
        }
        VerifyQuery verifyQuery = dto.toQuery();
        if (dto.getStatus() == ArticleStatusEnum.PASSED.getStatus()) {
            //todo 幻读问题需要处理
            //如果是通过审核,插入数据库
            VerifiedArticle verifiedArticle = verifyArticle.toArticle();
            if (verifiedArticleService.findById(verifiedArticle.getId()) == null) {
                verifiedArticleService.save(verifiedArticle);
                verifyQuery.setBindId(verifiedArticle.getId());
            }
        } else if (dto.getStatus() == ArticleStatusEnum.UNPASSED.getStatus()) {
            //如果是不通过,从数据库删除
            verifiedArticleService.removeById(verifyArticle.getBindId());//todo 需要处理删除失败情况
            verifyQuery.setBindId(0);
        }
        modifyState(verifyQuery);
    }

    void modifyState(VerifyQuery verifyQuery);

    List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery);
}
