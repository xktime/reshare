package com.xktime.model.mappers.article;

import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.entity.OriginalArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(OriginalArticle article);

    List<OriginalArticle> load(LoadQuery loadQuery);

    void modifyState(VerifyQuery verifyQuery);

    OriginalArticle findById(@Param("id") long id);

    void update(OriginalArticle article);
}
