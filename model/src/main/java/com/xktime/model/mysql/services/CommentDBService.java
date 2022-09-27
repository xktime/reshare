package com.xktime.model.mysql.services;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;
import com.xktime.model.services.ICommentDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import com.xktime.model.mysql.mappers.comment.*;

import java.util.List;

@Service
@ConditionalOnProperty(name = "spring.datasource.enable-mongo", havingValue = "false")
public class CommentDBService extends ICommentDBService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        commentMapper.saveComment(comment);
    }

    @Override
    public List<Comment> load(LoadQuery loadQuery) {
        return commentMapper.load(loadQuery);
    }
}
