package com.xktime.model.article.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求加载dto")
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("加载类型")
    String loadArticleType;

    @ApiModelProperty("当前用户token")
    String token;

    @ApiModelProperty("数据范围，比如频道ID")
    String tag;

}


