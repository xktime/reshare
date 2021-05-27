package com.xktime.model.mappers.article;

import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VerifiedArticleMapper {

    void saveArticle(VerifiedArticle verifiedArticle);

    List<VerifiedArticle> load(LoadQuery loadQuery);

    VerifiedArticle findById(@Param("id") long id);

    void removeById(@Param("id") long id);

    void update(VerifiedArticle verifiedArticle);
}
