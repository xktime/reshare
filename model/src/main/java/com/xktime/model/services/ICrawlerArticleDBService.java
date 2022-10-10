package com.xktime.model.services;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public abstract class ICrawlerArticleDBService {

    abstract public void saveArticle(CrawlerVerifyArticle article);

    abstract public void saveArticle(Collection<CrawlerVerifyArticle> articles);

    abstract public int getUrlCount(@Param("url") String url);

    abstract public List<CrawlerVerifyArticle> load(LoadQuery loadQuery);

    abstract public void modifyState(VerifyQuery verifyQuery);

    abstract public CrawlerVerifyArticle findById(@Param("id") long id);

    abstract public void update(CrawlerVerifyArticle article);
}
