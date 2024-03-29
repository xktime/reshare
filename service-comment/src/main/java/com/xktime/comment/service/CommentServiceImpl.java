package com.xktime.comment.service;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;
import com.xktime.model.services.ICommentDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    ICommentDBService commentDBService;

    @Override
    public void saveComment(Comment comment) {
        commentDBService.saveComment(comment);
    }

    @Override
    public List<Comment> load(LoadQuery loadQuery) {
        return commentDBService.load(loadQuery);
    }
}
