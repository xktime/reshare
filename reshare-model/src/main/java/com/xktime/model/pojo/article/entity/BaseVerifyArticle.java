package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import lombok.Data;

import java.util.Date;

@Data
public class BaseVerifyArticle extends BaseArticle {
    protected long id;
    protected String title;
    protected String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date publishTime;
    protected String authorName;
    protected String channelName;
    protected String labels;
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
        dto.setUrl(url);
        dto.setOrigin(origin);
        return dto;
    }

    public VerifiedArticle toArticle() {
        VerifiedArticle verifiedArticle = new VerifiedArticle();
        verifiedArticle.setId(id);
        verifiedArticle.setTitle(title);
        verifiedArticle.setContent(content);
        verifiedArticle.setAuthorName(authorName);
        verifiedArticle.setStatus(status);
        return verifiedArticle;
    }

    public SimpleArticleDto toSimpleArticleDto() {
        SimpleArticleDto dto = new SimpleArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        //OriginalArticle获取author需要单独处理
        return dto;
    }
}
