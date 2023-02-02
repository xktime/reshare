package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import com.xktime.model.services.IOriginalArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 平台发布文章
 */
@Service("OriginalArticle")
public class OriginalArticleServiceImpl extends BaseArticleService<OriginalVerifyArticle> implements BaseAuditable {

    @Autowired
    IOriginalArticleDBService originalArticleDBService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(OriginalVerifyArticle article) {
        originalArticleDBService.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<OriginalVerifyArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (OriginalVerifyArticle article : articles) {
            save(article);
        }
    }

    @Override
    public void update(OriginalVerifyArticle article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OriginalVerifyArticle findById(long id) {
        OriginalVerifyArticle article = redisUtil.mapGet(RedisCommonKey.ORIGINAL_ARTICLE, id);
        return article != null ? article : originalArticleDBService.findById(id);
    }

    @Override
    public List<OriginalVerifyArticle> loadArticles(LoadQuery loadQuery) {
        return originalArticleDBService.load(loadQuery);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<OriginalVerifyArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalVerifyArticle article : articles) {
                VerifyArticleDto verifyArticle = article.toVerifyArticleDto();
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<OriginalVerifyArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalVerifyArticle article : articles) {
                SimpleArticleDto simpleArticle = article.toSimpleArticleDto();
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        originalArticleDBService.modifyState(verifyQuery);
    }
}
