package com.xktime.model.mysql.services;

import com.xktime.model.mysql.mappers.article.VerifiedArticleMapper;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.services.IVerifiedArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class VerifiedArticleDBService extends IVerifiedArticleDBService {

    @Autowired
    VerifiedArticleMapper articleMapper;

    @Override
    public void saveArticle(VerifiedArticle verifiedArticle) {
        articleMapper.saveArticle(verifiedArticle);
    }

    @Override
    public List<VerifiedArticle> load(LoadQuery loadQuery) {
        return articleMapper.load(loadQuery);
    }

    @Override
    public VerifiedArticle findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public boolean removeById(long id) {
        articleMapper.removeById(id);//todo 需要返回值
        return true;
    }

    @Override
    public void update(VerifiedArticle verifiedArticle) {
        articleMapper.update(verifiedArticle);
    }
}
