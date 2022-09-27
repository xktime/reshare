package com.xktime.model.mysql.mappers.article;

import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(OriginalVerifyArticle article);

    List<OriginalVerifyArticle> load(LoadQuery loadQuery);

    void modifyState(VerifyQuery verifyQuery);

    OriginalVerifyArticle findById(@Param("id") long id);

    void update(OriginalVerifyArticle article);
}
