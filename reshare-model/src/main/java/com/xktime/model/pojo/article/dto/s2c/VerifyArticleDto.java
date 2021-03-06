package com.xktime.model.pojo.article.dto.s2c;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "被审核的文章")
public class VerifyArticleDto {
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
