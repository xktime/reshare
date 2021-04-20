package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.mappers.article.OriginalArticleMapper;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.OriginalArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("OriginalArticle")
public class OriginalArticleServiceImpl extends BaseAuditable implements BaseArticleService<OriginalArticle> {

    @Autowired
    OriginalArticleMapper originalArticleMapper;

    @Override
    public void save(OriginalArticle article) {
        originalArticleMapper.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<OriginalArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (OriginalArticle article : articles) {
            save(article);
        }
    }

    @Override
    public void update(OriginalArticle article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OriginalArticle findById(long id) {
        return originalArticleMapper.findById(id);
    }

    @Override
    public List<OriginalArticle> loadArticles(LoadQuery loadQuery) {
        return originalArticleMapper.load(loadQuery);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<OriginalArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalArticle article : articles) {
                VerifyArticleDto verifyArticle = article.toVerifyArticleDto();
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<OriginalArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalArticle article : articles) {
                SimpleArticleDto simpleArticle = article.toSimpleArticleDto();
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        originalArticleMapper.modifyState(verifyQuery);
    }
}
