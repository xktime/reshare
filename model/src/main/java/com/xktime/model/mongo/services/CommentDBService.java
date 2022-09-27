package com.xktime.model.mongo.services;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;
import com.xktime.model.services.ICommentDBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "true")
public class CommentDBService extends ICommentDBService {

    @Override
    public void saveComment(Comment comment) {

    }

    @Override
    public List<Comment> load(LoadQuery loadQuery) {
        return null;
    }
}
