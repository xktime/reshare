package com.xktime.model.article.dtos.s2c;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "首页展示dto")
public class SimpleArticleDto {
    private long id;
    private String title;
    private String authorName;
    private long authorId;
    private String channelName;
    private String labels;
    private Date publishTime;
    private String url;
    private String origin;
}
