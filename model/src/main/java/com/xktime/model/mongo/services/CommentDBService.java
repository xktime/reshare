package com.xktime.model.mongo.services;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.services.ICommentDBService;
import io.netty.util.internal.StringUtil;
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
public class CommentDBService extends ICommentDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveComment(Comment comment) {
        mongoTemplate.save(comment);
    }

    @Override
    public List<Comment> load(LoadQuery loadQuery) {
        Criteria criteria = new Criteria();
        if (loadQuery.getBindId() > 0){
            criteria = criteria.and("bindId").is(loadQuery.getBindId());
        }
        if (!StringUtil.isNullOrEmpty(loadQuery.getLoadCommentType())){
            criteria = criteria.and("loadCommentType").is(loadQuery.getLoadCommentType());
        }
        Pageable pageable = PageRequest.of(loadQuery.getPageStartIndex(), loadQuery.getSize(), Sort.by(Sort.Order.desc("publishTime")));
        Query query = Query.query(criteria).with(pageable);
        return mongoTemplate.find(query, Comment.class);
    }
}
