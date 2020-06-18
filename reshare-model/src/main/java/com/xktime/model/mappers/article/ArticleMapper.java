package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.user.pojos.UserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    List<Article> loadArticleListByLocation(@Param("dto") LoadArticleDto dto);

    List<Article> loadArticleListByIdList(@Param("list")List<UserArticleList> list);

    void saveArticle(Article article);

}
