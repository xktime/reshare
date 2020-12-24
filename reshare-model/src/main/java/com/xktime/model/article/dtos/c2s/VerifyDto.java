package com.xktime.model.article.dtos.c2s;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求审核文章dto")
public class VerifyDto {
    @ApiModelProperty("文章id")
    long articleId;
    @ApiModelProperty("文章状态 0为未审核 1为未通过 2为通过")
    int status;
    @ApiModelProperty("加载文章的类型")
    int type;
}
