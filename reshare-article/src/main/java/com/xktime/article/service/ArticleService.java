package com.xktime.article.service;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    void saveCrawlerArticleList(List<CrawlerArticle> articleList);

    void saveCrawlerArticle(CrawlerArticle article);

    int getCrawlerArticleUrlCount(@Param("url") String url);

    List<CrawlerArticle> loadCrawlerArticleList(LoadArticleDto dto);

    List<VerifyArticleDto> loadVerifyArticleDtoList(LoadArticleDto dto);

    void verify(VerifyDto dto);

}
