package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class CrawlerArticleDBService extends ICrawlerArticleDBService {

    @Override
    public void saveArticle(CrawlerVerifyArticle article) {

    }

    @Override
    public int getUrlCount(String url) {
        return 0;
    }

    @Override
    public List<CrawlerVerifyArticle> load(LoadQuery loadQuery) {
        return null;
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {

    }

    @Override
    public CrawlerVerifyArticle findById(long id) {
        return null;
    }

    @Override
    public void update(CrawlerVerifyArticle article) {

    }
}
