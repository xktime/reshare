package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.IOriginalArticleDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class OriginalArticleDBService extends IOriginalArticleDBService {
    @Override
    public void saveArticle(OriginalVerifyArticle article) {

    }

    @Override
    public List<OriginalVerifyArticle> load(LoadQuery loadQuery) {
        return null;
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {

    }

    @Override
    public OriginalVerifyArticle findById(long id) {
        return null;
    }

    @Override
    public void update(OriginalVerifyArticle article) {

    }
}
