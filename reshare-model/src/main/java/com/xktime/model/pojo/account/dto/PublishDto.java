package com.xktime.model.pojo.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求发布dto")
public class PublishDto {
    @ApiModelProperty("文章标题")
    String title;
    @ApiModelProperty("文章内容")
    String content;
    @ApiModelProperty("当前账户token")
    String token;
}
