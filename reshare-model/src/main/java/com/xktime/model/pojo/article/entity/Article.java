package com.xktime.model.pojo.article.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.comment.entity.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 审核通过的文章
 */
@Data
public class Article {
    private long id;
    private String title;
    private long authorId;
    private String authorName;
    private int channelId;
    private String channelName;
    private String images;
    private String labels;
    private int likes;
    private int collection;
    private int comment;
    private int views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String origin;
    private String content;
    private String tag;
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
        dto.setComment(comment);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        dto.setViews(views);
        return dto;
    }

    public ArticleDetailsDto toArticleDetailsDto(List<Comment> comments) {
        //todo 插入detail评论整合到TransferUtils
        ArticleDetailsDto dto = new ArticleDetailsDto();
        dto.setAuthorId(authorId);
        dto.setAuthorName(authorName);
        dto.setChannelId(channelId);
        dto.setChannelName(channelName);
        dto.setCollection(collection);
        dto.setComment(comment);
        dto.setViews(views);
        dto.setTitle(title);
        dto.setPublishTime(publishTime);
        dto.setLabels(labels);
        dto.setLikes(likes);
        dto.setOrigin(origin);
        dto.setTag(tag);
        dto.setContent(content);
        dto.setId(id);
        dto.setImages(images);
        //todo 转换commentDto
        new CommentDto();
        return dto;
    }
}