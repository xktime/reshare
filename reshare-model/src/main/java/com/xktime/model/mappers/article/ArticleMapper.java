package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.pojos.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    long saveArticle(Article article);

    List<Article> load(LoadDto dto);

    Article findById(@Param("id") long id);

    void deleteById(@Param("id") long id);

    long update(Article article);
}
