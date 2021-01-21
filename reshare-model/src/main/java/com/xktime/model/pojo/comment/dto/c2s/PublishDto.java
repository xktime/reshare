package com.xktime.model.pojo.comment.dto.c2s;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求发布评论dto")
public class PublishDto {
    @ApiModelProperty("绑定ID(文章为article_id)")
    private long bindId;
    @ApiModelProperty("评论类型")
    private int type;
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("当前账户token")
    private String token;
}
