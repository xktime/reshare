package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.services.IVerifiedArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class VerifiedArticleDBService extends IVerifiedArticleDBService {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public void saveArticle(VerifiedArticle verifiedArticle) {
        mongoTemplate.insert(verifiedArticle);
    }

    @Override
    public List<VerifiedArticle> load(LoadQuery loadQuery) {
        Criteria criteria = Criteria.where("publishTime").lte(loadQuery.getLastTime());
        Pageable pageable = PageRequest.of(loadQuery.getPageStartIndex(), loadQuery.getSize(), Sort.by(Sort.Order.desc("publishTime")));
        Query query = Query.query(criteria).with(pageable);
        return mongoTemplate.find(query, VerifiedArticle.class);
    }

    @Override
    public VerifiedArticle findById(long id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, VerifiedArticle.class);
    }

    @Override
    public void removeById(long id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, VerifiedArticle.class);
    }

    @Override
    public void update(VerifiedArticle verifiedArticle) {
        mongoTemplate.save(verifiedArticle);
    }
}
