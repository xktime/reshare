package com.xktime.model.mysql.services;

import com.xktime.model.mysql.mappers.article.OriginalArticleMapper;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.IOriginalArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class OriginalArticleDBService extends IOriginalArticleDBService {

    @Autowired
    OriginalArticleMapper articleMapper;

    @Override
    public void saveArticle(OriginalVerifyArticle article) {
        articleMapper.saveArticle(article);
    }

    @Override
    public List<OriginalVerifyArticle> load(LoadQuery loadQuery) {
        return articleMapper.load(loadQuery);
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        articleMapper.modifyState(verifyQuery);
    }

    @Override
    public OriginalVerifyArticle findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public void update(OriginalVerifyArticle article) {
        articleMapper.update(article);
    }
}
