package com.xktime.model.mysql.mappers.article;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrawlerArticleMapper {

    void saveArticle(CrawlerVerifyArticle article);

    int getUrlCount(@Param("url") String url);

    List<CrawlerVerifyArticle> load(LoadQuery loadQuery);

    void modifyState(VerifyQuery verifyQuery);

    CrawlerVerifyArticle findById(@Param("id") long id);

    void update(CrawlerVerifyArticle article);
}
