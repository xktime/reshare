package com.xktime.model.pojo.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private long id;
    private long authorId;
    private long bindId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private int type;

    //todo 实现toCommentDto
    public CommentDto toCommentDto() {
        return null;
    }

}
