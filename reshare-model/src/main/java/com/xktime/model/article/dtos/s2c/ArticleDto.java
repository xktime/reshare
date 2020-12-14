package com.xktime.model.article.dtos.s2c;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "审核通过的文章")
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private String authorName;
    private String channelName;
    private String labels;
    private Date publishTime;
    private String url;
    private String origin;
    private int status;
}
