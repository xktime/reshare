package com.xktime.model.pojo.article.dto.s2c;

import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "首页展示dto")
public class SimpleArticleDto implements Serializable {
    private long id;
    private String title;
    private SimpleUserDto author;
    private String channelName;
    private Date publishTime;
    private int comment;
    private int views;
}
