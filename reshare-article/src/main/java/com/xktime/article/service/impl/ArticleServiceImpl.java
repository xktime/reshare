package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    CrawlerArticleMapper crawlerArticleMapper;


    @Override
    public void saveCrawlerArticleList(List<CrawlerArticle> articleList) {
        if (articleList == null || articleList.isEmpty()) {
            return;
        }
        for (CrawlerArticle article : articleList) {
            saveCrawlerArticle(article);
        }
    }

    @Override
    public void saveCrawlerArticle(CrawlerArticle article) {
        crawlerArticleMapper.saveArticle(article);
    }

    @Override
    public int getCrawlerArticleUrlCount(String url) {
        return crawlerArticleMapper.getUrlCount(url);
    }

    @Override
    public List<CrawlerArticle> loadCrawlerArticleList(LoadArticleDto dto) {
        return crawlerArticleMapper.load(dto);
    }

    @Override
    public void verify(VerifyDto dto) {
        crawlerArticleMapper.verify(dto);
    }
}
