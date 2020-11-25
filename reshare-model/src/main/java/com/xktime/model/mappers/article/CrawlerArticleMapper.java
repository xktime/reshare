package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrawlerArticleMapper {

    void saveArticle(CrawlerArticle article);

    int getUrlCount(@Param("url") String url);

    List<CrawlerArticle> load(LoadDto dto);

    void modifyState(VerifyDto dto);

    CrawlerArticle findById(@Param("id") long id);

    void update(CrawlerArticle article);
}
