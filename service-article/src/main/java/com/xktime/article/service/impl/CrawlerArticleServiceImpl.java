package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.mysql.services.CrawlerArticleDBService;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 爬虫爬取的文章
 */
@Service("CrawlerArticle")
public class CrawlerArticleServiceImpl extends BaseAuditable implements BaseArticleService<CrawlerVerifyArticle> {
    @Autowired
    ICrawlerArticleDBService crawlerArticleDBService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(CrawlerVerifyArticle article) {
        crawlerArticleDBService.saveArticle(article);
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
        crawlerArticleDBService.update(article);
    }

    @Override
    public CrawlerVerifyArticle findById(long id) {
        CrawlerVerifyArticle article = redisUtil.mapGet(RedisCommonKey.CRAWLER_ARTICLE, id);
        return article != null ? article : crawlerArticleDBService.findById(id);
    }

    @Override
    public List<CrawlerVerifyArticle> loadArticles(LoadQuery loadQuery) {
        return crawlerArticleDBService.load(loadQuery);
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
        crawlerArticleDBService.modifyState(verifyQuery);
    }

    public int getUrlCount(String url) {
        return crawlerArticleDBService.getUrlCount(url);
    }
}
