package com.xktime.model.services;

import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract class IOriginalArticleDBService {

    abstract public void saveArticle(OriginalVerifyArticle article);

    abstract public List<OriginalVerifyArticle> load(LoadQuery loadQuery);

    abstract public void modifyState(VerifyQuery verifyQuery);

    abstract public OriginalVerifyArticle findById(@Param("id") long id);

    abstract public void update(OriginalVerifyArticle article);
}
