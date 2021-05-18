package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.common.RedisCommonKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("CrawlerArticle")
public class CrawlerArticleServiceImpl extends BaseAuditable implements BaseArticleService<CrawlerVerifyArticle> {
    @Autowired
    CrawlerArticleMapper crawlerArticleMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(CrawlerVerifyArticle article) {
        crawlerArticleMapper.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<CrawlerVerifyArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (CrawlerVerifyArticle article : articles) {
            save(article);
        }
    }

    @Override
    public void update(CrawlerVerifyArticle article) {
        crawlerArticleMapper.update(article);
    }

    @Override
    public CrawlerVerifyArticle findById(long id) {
        CrawlerVerifyArticle article = redisUtil.mapGet(RedisCommonKey.CRAWLER_ARTICLE, id);
        return article != null ? article : crawlerArticleMapper.findById(id);
    }

    @Override
    public List<CrawlerVerifyArticle> loadArticles(LoadQuery loadQuery) {
        return crawlerArticleMapper.load(loadQuery);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<CrawlerVerifyArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (CrawlerVerifyArticle article : articles) {
                VerifyArticleDto verifyArticle = article.toVerifyArticleDto();
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<CrawlerVerifyArticle> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (CrawlerVerifyArticle article : articles) {
                SimpleArticleDto simpleArticle = article.toSimpleArticleDto();
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
