package com.xktime.article.service;

import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrawlerArticleService {

    void save(List<CrawlerArticle> articleList);

    Integer findByUrl(@Param("url") String url);
}
