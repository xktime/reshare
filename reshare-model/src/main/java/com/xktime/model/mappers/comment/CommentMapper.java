package com.xktime.model.mappers.comment;

import com.xktime.model.comment.dos.LoadDo;
import com.xktime.model.comment.pos.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    void saveComment(Comment comment);

    List<Comment> load(LoadDo loadDo);
}
