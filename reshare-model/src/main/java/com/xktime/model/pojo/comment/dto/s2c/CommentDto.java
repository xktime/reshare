package com.xktime.model.pojo.comment.dto.s2c;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private long id;
    private SimpleUserDto author;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
}
