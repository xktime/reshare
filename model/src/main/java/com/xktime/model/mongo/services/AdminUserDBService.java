package com.xktime.model.mongo.services;

import com.xktime.model.pojo.user.entity.AdminUser;
import com.xktime.model.services.IAdminUserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class AdminUserDBService extends IAdminUserDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveUser(AdminUser user) {
        mongoTemplate.insert(user);
    }

    @Override
    public AdminUser queryByAccount(String account) {
        Query query = Query.query(Criteria.where("account").is(account));
        return mongoTemplate.findOne(query, AdminUser.class);
    }
}
