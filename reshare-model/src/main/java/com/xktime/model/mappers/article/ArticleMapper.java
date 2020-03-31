package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.user.pojos.UserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    List<Article> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto);

    List<Article> loadArticleListByIdList(@Param("list")List<UserArticleList> list);
}
