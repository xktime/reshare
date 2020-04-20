package com.xktime.model.article.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class CrawlerArticle {
    private Integer id;
    private String title;
    private String content;
    private String authorName;
    private String channelName;
    private String labels;
    private Date publishTime;
    private String url;
    private String origin;
}
