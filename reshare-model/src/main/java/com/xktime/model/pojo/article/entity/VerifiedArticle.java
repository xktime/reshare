package com.xktime.model.pojo.article.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.comment.type.CommentTypeEnum;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 审核通过的文章
 */
@Data
public class VerifiedArticle extends BaseArticle {
    private long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
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
    private String origin;
    private String tag;
    private int status;

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

    public ArticleDetailsDto toArticleDetailsDto(List<CommentDto> comments, SimpleUserDto author) {
        ArticleDetailsDto dto = new ArticleDetailsDto();
        dto.setAuthor(author);
        dto.setComments(comments);
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
        return dto;
    }

    public LoadDto getCommentLoadDto() {
        LoadDto loadDto = new LoadDto();
        loadDto.setBindId(id);
        loadDto.setLoadCommentType(CommentTypeEnum.ARTICLE.getType());
        return loadDto;
    }
}