package com.xktime.model.pojo.article.entity;

import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import lombok.Data;

/**
 * 爬虫爬取的文章
 */
@Data
public class CrawlerArticle extends BaseVerifyArticle {

    public SimpleArticleDto toSimpleArticleDto() {
        SimpleArticleDto dto = new SimpleArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        return dto;
    }
}
