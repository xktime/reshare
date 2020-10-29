package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;

import java.util.Collection;
import java.util.List;

/**
 * 首页显示文章
 */
public class ArticleServiceImpl implements BaseArticleService<CrawlerArticle> {
    @Override
    public void save(CrawlerArticle article) {

    }

    @Override
    public void saveArticles(Collection<CrawlerArticle> articles) {

    }

    @Override
    public List<CrawlerArticle> loadArticles(LoadDto dto) {
        return null;
    }

    @Override
    public void verify(VerifyDto dto) {

    }
}
