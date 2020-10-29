package com.xktime.model.article.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 爬虫爬取的文章
 */
@Data
public class CrawlerArticle implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String channelName;
    private String labels;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String url;
    private String origin;
    private int status;

}
