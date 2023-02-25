package com.xktime.model.services;

import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract class IVerifiedArticleDBService {

    abstract public void saveArticle(VerifiedArticle verifiedArticle);

    abstract public List<VerifiedArticle> load(LoadQuery loadQuery);

    abstract public VerifiedArticle findById(long id);

    abstract public boolean removeById(long id);

    abstract public void update(VerifiedArticle verifiedArticle);
}
