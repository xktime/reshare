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

    @ApiModelProperty("加载类型 爬取文章:crawler 原创文章:original 用户推荐文章:recommend")
    String loadArticleType;

    @ApiModelProperty("当前用户token")
    String token;

}


