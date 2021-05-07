package com.xktime.model.pojo.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
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

    public CommentDto toCommentDto(SimpleUserDto author) {
        CommentDto dto = new CommentDto();
        dto.setContent(content);
        dto.setPublishTime(publishTime);
        dto.setId(id);
        dto.setAuthor(author);
        return dto;
    }

}
