package com.xktime.model.pojo.article.dto.c2s;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求发布文章dto")
public class PublishDto {
    @ApiModelProperty("文章标题")
    String title;
    @ApiModelProperty("文章内容")
    String content;
    @ApiModelProperty("当前账户token")
    String token;
}
