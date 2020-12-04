package com.xktime.model.mappers.article;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.pos.CrawlerArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrawlerArticleMapper {

    void saveArticle(CrawlerArticle article);

    int getUrlCount(@Param("url") String url);

    List<CrawlerArticle> load(LoadDo loadDo);

    void modifyState(VerifyDo verifyDo);

    CrawlerArticle findById(@Param("id") long id);

    void update(CrawlerArticle article);
}
