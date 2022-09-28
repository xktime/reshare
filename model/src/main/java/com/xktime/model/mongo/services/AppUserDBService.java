package com.xktime.model.mongo.services;

import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.services.IAppUserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class AppUserDBService extends IAppUserDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveUser(AppUser user) {
        mongoTemplate.save(user);
    }

    @Override
    public AppUser queryByAccount(String account) {
        Query query = Query.query(Criteria.where("account").is(account));
        return mongoTemplate.findOne(query, AppUser.class);
    }

    @Override
    public AppUser queryByUserId(long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, AppUser.class);
    }
}
