package com.xktime.model.mongo.services;

import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.pojo.user.entity.UserArticleList;
import com.xktime.model.services.IUserArticleListDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class UserArticleListDBService extends IUserArticleListDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<UserArticleList> loadArticleIdListByUser(AppUser appUser, LoadDto dto) {
        return null;
    }
}
