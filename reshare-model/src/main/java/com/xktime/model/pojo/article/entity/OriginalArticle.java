package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import lombok.Data;

import java.util.Date;


/**
 * 平台原创文章
 */
@Data
public class OriginalArticle extends BaseVerifyArticle {
    private long id;
    private String title;
    private String content;
    private String authorName;
    private long authorId;
    private String channelName;
    private String labels;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String url;
    private String origin;
    private int status;

    public VerifyArticleDto toVerifyArticleDto() {
        VerifyArticleDto dto = new VerifyArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setAuthorName(authorName);
        dto.setStatus(status);
        dto.setLabels(labels);
        return dto;
    }

    public SimpleArticleDto toSimpleArticleDto() {
        SimpleArticleDto dto = new SimpleArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        return dto;
    }
}
