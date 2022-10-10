package com.xktime.model.mysql.services;

import com.xktime.model.mysql.mappers.article.CrawlerArticleMapper;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class CrawlerArticleDBService extends ICrawlerArticleDBService {

    @Autowired
    CrawlerArticleMapper articleMapper;
    @Override
    public void saveArticle(CrawlerVerifyArticle article) {
        articleMapper.saveArticle(article);
    }

    @Override
    public void saveArticle(Collection<CrawlerVerifyArticle> articles) {
        //todo 暂未实现
    }

    @Override
    public int getUrlCount(String url) {
        return articleMapper.getUrlCount(url);
    }

    @Override
    public List<CrawlerVerifyArticle> load(LoadQuery loadQuery) {
        return articleMapper.load(loadQuery);
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        articleMapper.modifyState(verifyQuery);
    }

    @Override
    public CrawlerVerifyArticle findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public void update(CrawlerVerifyArticle article) {
        articleMapper.update(article);
    }
}
