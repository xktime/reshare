package com.xktime.model.pojo.article.dto.s2c;

import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ArticleDetailsDto implements Serializable {
    private long id;
    private String title;
    private SimpleUserDto author;
    private int channelId;
    private String channelName;
    private String images;
    private String labels;
    private int likes;
    private int collection;
    private int comment;
    private int views;
    private long publishTime;
    private String origin;
    private List<String> content;
    private String tag;
    private List<CommentDto> comments;
}
