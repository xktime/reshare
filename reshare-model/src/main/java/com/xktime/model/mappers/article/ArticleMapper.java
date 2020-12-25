package com.xktime.model.mappers.article;

import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    void saveArticle(Article article);

    List<Article> load(LoadQuery loadQuery);

    Article findById(@Param("id") long id);

    void removeById(@Param("id") long id);

    void update(Article article);
}
