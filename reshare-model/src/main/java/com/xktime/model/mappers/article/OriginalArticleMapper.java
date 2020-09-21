package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.pojos.OriginalArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(OriginalArticle article);

    List<OriginalArticle> load(LoadArticleDto dto);

}
