package com.xktime.article.service.impl;

import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlerArticleServiceImpl implements CrawlerArticleService {

    @Autowired
    CrawlerArticleMapper articleMapper;

    @Override
    public void save(CrawlerArticle article) {
        articleMapper.saveArticle(article);
    }

    @Override
    public int getUrlCount(String url) {
        return articleMapper.getUrlCount(url);
    }

    @Override
    public List<CrawlerArticle> load() {
        return articleMapper.load();
    }

    @Override
    public void audit(int articleId, int status) {
        articleMapper.audit(articleId, status);
    }

    @Override
    public void save(List<CrawlerArticle> articleList) {
        if (articleList == null || articleList.isEmpty()) {
            return;
        }
        for (CrawlerArticle article : articleList) {
            save(article);
        }
    }

}
