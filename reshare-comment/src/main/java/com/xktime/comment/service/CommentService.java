package com.xktime.comment.service;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> load(LoadQuery loadQuery);
}
