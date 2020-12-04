package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.pos.CrawlerArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("CrawlerArticle")
public class CrawlerBaseArticleServiceImpl extends BaseAuditable implements BaseArticleService<CrawlerArticle> {
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
    public List<CrawlerArticle> loadArticles(LoadDo loadDo) {
        return crawlerArticleMapper.load(loadDo);
    }

    @Override
    public void modifyState(VerifyDo verifyDo) {
        crawlerArticleMapper.modifyState(verifyDo);
    }

    public int getUrlCount(String url) {
        return crawlerArticleMapper.getUrlCount(url);
    }
}
