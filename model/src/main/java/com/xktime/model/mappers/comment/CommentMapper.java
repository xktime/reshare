package com.xktime.model.mappers.comment;

import com.xktime.model.pojo.comment.query.LoadQuery;
import com.xktime.model.pojo.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    void saveComment(Comment comment);

    List<Comment> load(LoadQuery loadQuery);
}
