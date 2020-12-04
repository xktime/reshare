package com.xktime.model.mappers.article;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.pos.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    void saveArticle(Article article);

    List<Article> load(LoadDo loadDo);

    Article findById(@Param("id") long id);

    void removeById(@Param("id") long id);

    void update(Article article);
}
