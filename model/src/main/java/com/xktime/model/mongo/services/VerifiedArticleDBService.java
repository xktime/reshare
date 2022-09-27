package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.services.IVerifiedArticleDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class VerifiedArticleDBService extends IVerifiedArticleDBService {
    @Override
    public void saveArticle(VerifiedArticle verifiedArticle) {

    }

    @Override
    public List<VerifiedArticle> load(LoadQuery loadQuery) {
        return null;
    }

    @Override
    public VerifiedArticle findById(long id) {
        return null;
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public void update(VerifiedArticle verifiedArticle) {

    }
}
