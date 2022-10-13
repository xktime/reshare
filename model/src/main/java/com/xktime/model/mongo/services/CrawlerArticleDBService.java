package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.services.ICrawlerArticleDBService;
import com.xktime.utils.util.SnowflakeIdUtil;
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

import java.util.Collection;
import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class CrawlerArticleDBService extends ICrawlerArticleDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveArticle(CrawlerVerifyArticle article) {
        mongoTemplate.insert(article);
    }

    @Override
    public void saveArticle(Collection<CrawlerVerifyArticle> articles) {
        mongoTemplate.insert(articles, CrawlerVerifyArticle.class);
    }

    @Override
    public int getUrlCount(String url) {
        Query query = Query.query(Criteria.where("url").is(url));
        return mongoTemplate.find(query, CrawlerVerifyArticle.class).size();
    }

    @Override
    public List<CrawlerVerifyArticle> load(LoadQuery loadQuery) {
        //todo 数据库时间存储改为时间戳
//        Criteria criteria = Criteria.where("publish_time").lte(loadQuery.getLastTime());
//        Pageable pageable = PageRequest.of(loadQuery.getPageStartIndex(), loadQuery.getSize(), Sort.by(Sort.Order.asc("publish_time")));
        Criteria criteria = Criteria.where("_id").gt(1);
        Pageable pageable = PageRequest.of(loadQuery.getPageStartIndex(), loadQuery.getSize(), Sort.by(Sort.Order.asc("_id")));
        Query query = Query.query(criteria).with(pageable);
        return mongoTemplate.find(query, CrawlerVerifyArticle.class);
    }

    @Override
    public void modifyState(VerifyQuery verifyQuery) {
        Update update = Update.update("status", verifyQuery.getStatus()).set("bind_id", verifyQuery.getBindId());
        Query query = Query.query(Criteria.where("_id").is(verifyQuery.getArticleId()));
        mongoTemplate.findAndModify(query, update, CrawlerVerifyArticle.class);
    }

    @Override
    public CrawlerVerifyArticle findById(long id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, CrawlerVerifyArticle.class);
    }

    @Override
    public void update(CrawlerVerifyArticle article) {
        mongoTemplate.save(article);
    }
}
