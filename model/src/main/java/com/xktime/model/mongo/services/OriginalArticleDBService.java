package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.IOriginalArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class OriginalArticleDBService extends IOriginalArticleDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveArticle(OriginalVerifyArticle article) {
        mongoTemplate.insert(article);
    }

    @Override
    public List<OriginalVerifyArticle> load(LoadQuery loadQuery) {
        Criteria criteria = Criteria.where("publishTime").lte(loadQuery.getLastTime());
        Pageable pageable = PageRequest.of(loadQuery.getPageStartIndex(), loadQuery.getSize(), Sort.by(Sort.Order.desc("publishTime")));
        Query query = Query.query(criteria).with(pageable);
        return mongoTemplate.find(query, OriginalVerifyArticle.class);
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        Update update = Update.update("status", verifyQuery.getStatus()).set("bindId", verifyQuery.getBindId());
        Query query = Query.query(Criteria.where("_id").is(verifyQuery.getArticleId()));
        mongoTemplate.findAndModify(query, update, OriginalVerifyArticle.class);
    }

    @Override
    public OriginalVerifyArticle findById(long id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, OriginalVerifyArticle.class);
    }

    @Override
    public void update(OriginalVerifyArticle article) {
        mongoTemplate.save(article);
    }
}
