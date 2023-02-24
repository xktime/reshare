package com.xktime.model.pojo.article.dto.s2c;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "被审核的文章")
public class VerifyArticleDto implements Serializable {
    private long id;
    private String title;
    private List<String> content;
    private String authorName;
    private String channelName;
    private String labels;
    private long publishTime;
    private String url;
    private String origin;
    private int status;
}
