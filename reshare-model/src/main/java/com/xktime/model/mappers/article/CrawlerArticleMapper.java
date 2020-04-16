package com.xktime.model.mappers.article;

import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CrawlerArticleMapper {

    void saveArticle(CrawlerArticle article);
}
