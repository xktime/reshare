package com.xktime.model.mappers.article;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.pos.OriginalArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OriginalArticleMapper {

    void saveArticle(OriginalArticle article);

    List<OriginalArticle> load(LoadDo loadDo);

    void modifyState(VerifyDo verifyDo);

    OriginalArticle findById(@Param("id") long id);

    void update(OriginalArticle article);
}
