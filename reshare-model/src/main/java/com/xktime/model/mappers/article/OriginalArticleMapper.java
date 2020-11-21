package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.OriginalArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(OriginalArticle article);

    List<OriginalArticle> load(LoadDto dto);

    void modifyState(VerifyDto dto);

    OriginalArticle findById(@Param("id") int id);
}
