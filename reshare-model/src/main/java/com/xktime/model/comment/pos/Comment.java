package com.xktime.model.comment.pos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private long id;
    private long authorId;
    private long articleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String content;
}
