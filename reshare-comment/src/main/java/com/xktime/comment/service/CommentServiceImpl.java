package com.xktime.comment.service;

import com.xktime.model.mappers.comment.CommentMapper;
import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService{
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
