package com.xktime.model.article.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String url;
    private String origin;
}
