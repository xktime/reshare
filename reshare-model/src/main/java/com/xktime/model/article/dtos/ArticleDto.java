package com.xktime.model.article.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDto {
    private Integer id;
    private String title;
    private String content;
    private String authorName;
    private String channelName;
    private String labels;
    private Date publishTime;
    private String url;
    private String origin;
    private int status;
}
