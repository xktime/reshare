package com.xktime.model.services;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.comment.query.LoadQuery;

import java.util.List;

public abstract class ICommentDBService {

    abstract public void saveComment(Comment comment);

    abstract public List<Comment> load(LoadQuery loadQuery);
}
