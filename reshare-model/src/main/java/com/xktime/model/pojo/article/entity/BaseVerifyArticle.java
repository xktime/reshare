package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseVerifyArticle implements Serializable {
    protected long id;
    protected String title;
    protected String content;
    protected String authorName;
    protected String channelName;
    protected String labels;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date publishTime;
    protected String url;
    protected String origin;
    protected int status;
    protected long bindId;

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

    public Article toArticle() {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthorName(authorName);
        article.setStatus(status);
        return article;
    }

    //todo 子类继承toSimpleArticleDto和toVerifyArticleDto
}
