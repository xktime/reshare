package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(CrawlerArticle article);

    List<CrawlerArticle> load(LoadDto dto);

    void verify(VerifyDto dto);
}
