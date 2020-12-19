package com.xktime.model.article.dtos.s2c;

import com.xktime.model.user.dtos.s2c.SimpleUserDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "首页展示dto")
public class SimpleArticleDto {
    private long id;
    private String title;
    private SimpleUserDto author;
    private String channelName;
    private Date publishTime;
    private int comment;
    private int views;
}
