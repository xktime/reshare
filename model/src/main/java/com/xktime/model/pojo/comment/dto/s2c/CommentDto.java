package com.xktime.model.pojo.comment.dto.s2c;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto implements Serializable {
    private long id;
    private SimpleUserDto author;
    private String content;
    private long publishTime;
}
