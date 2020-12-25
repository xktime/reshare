package com.xktime.model.mappers.article;

import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.entity.CrawlerArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrawlerArticleMapper {

    void saveArticle(CrawlerArticle article);

    int getUrlCount(@Param("url") String url);

    List<CrawlerArticle> load(LoadQuery loadQuery);

    void modifyState(VerifyQuery verifyQuery);

    CrawlerArticle findById(@Param("id") long id);

    void update(CrawlerArticle article);
}
