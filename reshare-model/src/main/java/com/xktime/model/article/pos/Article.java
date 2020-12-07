package com.xktime.model.article.pos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 审核通过的文章
 */
@Data
public class Article {
    private long id;
    private String title;
    private byte[] authorId;
    private String authorName;
    private int channelId;
    private String channelName;
    private String images;
    private String labels;
    private int likes;
    private int collection;
    private int comment;
    private int views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String origin;
    private String content;
    private String tag;
    private int status;
}