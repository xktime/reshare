package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import lombok.Data;

import java.util.Date;

/**
 * 爬虫爬取的文章
 */
@Data
public class CrawlerArticle extends BaseVerifyArticle {
    private long id;
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

    public SimpleArticleDto toSimpleArticleDto() {
        SimpleArticleDto dto = new SimpleArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        return dto;
    }
}
