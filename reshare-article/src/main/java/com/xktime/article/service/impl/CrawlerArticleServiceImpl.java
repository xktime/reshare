package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.CrawlerArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import com.xktime.model.util.TransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("CrawlerArticle")
public class CrawlerArticleServiceImpl extends BaseAuditable implements BaseArticleService<CrawlerArticle> {
    @Autowired
    CrawlerArticleMapper crawlerArticleMapper;

    @Override
    public void save(CrawlerArticle article) {
        crawlerArticleMapper.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<CrawlerArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (CrawlerArticle article : articles) {
            save(article);
        }
    }

    @Override
    public void update(CrawlerArticle article) {
        crawlerArticleMapper.update(article);
    }

    @Override
    public CrawlerArticle findById(long id) {
        return crawlerArticleMapper.findById(id);
    }

    @Override
    public List<CrawlerArticle> loadArticles(LoadQuery loadQuery) {
        return crawlerArticleMapper.load(loadQuery);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<CrawlerArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (CrawlerArticle article : articles) {
                VerifyArticleDto verifyArticle = TransferUtils.toVerifyArticleDto(article);
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<CrawlerArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (CrawlerArticle article : articles) {
                SimpleArticleDto simpleArticle = TransferUtils.toSimpleArticleDto(article);
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        crawlerArticleMapper.modifyState(verifyQuery);
    }

    public int getUrlCount(String url) {
        return crawlerArticleMapper.getUrlCount(url);
    }
}
