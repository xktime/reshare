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
    public void save(List<CrawlerArticle> articleList) {
        if (articleList == null || articleList.isEmpty()) {
            return;
        }
        for (CrawlerArticle article : articleList) {
            articleMapper.saveArticle(article);
        }
    }

    @Override
    public Integer findByUrl(String url) {
        return articleMapper.findByUrl(url);
    }
}
