package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.pojos.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    void saveArticle(Article article);

    List<Article> load(LoadDto dto);

}
