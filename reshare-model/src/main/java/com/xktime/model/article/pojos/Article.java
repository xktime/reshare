package com.xktime.model.article.pojos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Long id;
    private String title;
    private String authorId;
    private String authorName;
    private Integer channelId;
    private String channelName;
    private String images;
    private String labels;
    private Integer likes;
    private Integer collection;
    private Integer comment;
    private Integer views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String origin;
    private String content;
    private int status;
}