package com.xktime.model.article.dtos.s2c;

import com.xktime.model.comment.dtos.s2c.CommentDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDetailsDto {
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
    private Date publishTime;
    private String origin;
    private String content;
    private String tag;
    private List<CommentDto> comments;
}
