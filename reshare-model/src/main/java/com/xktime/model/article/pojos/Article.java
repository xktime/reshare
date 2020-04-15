package com.xktime.model.article.pojos;


import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private Long authorId;
    private String authorName;
    private Integer channelId;
    private String channelName;
    private String images;
    private String labels;
    private Integer likes;
    private Integer collection;
    private Integer comment;
    private Integer views;
    private Date publishTime;
    private String origin;
    private String content;
}