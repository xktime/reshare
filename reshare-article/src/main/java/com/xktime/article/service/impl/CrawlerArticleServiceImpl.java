package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("CrawlerArticle")
public class CrawlerArticleServiceImpl implements ArticleService<CrawlerArticle> {
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
    public List<CrawlerArticle> loadArticles(LoadArticleDto dto) {
        return crawlerArticleMapper.load(dto);
    }

    @Override
    public void verify(VerifyDto dto) {
        crawlerArticleMapper.verify(dto);
    }

    public int getUrlCount(String url) {
        return crawlerArticleMapper.getUrlCount(url);
    }
}
