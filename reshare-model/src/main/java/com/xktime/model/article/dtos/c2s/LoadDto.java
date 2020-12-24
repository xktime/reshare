package com.xktime.model.article.dtos.c2s;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求加载文章dto")
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("加载类型 爬取文章:1;原创文章:2;用户推荐文章:3")
    int loadArticleType;

    @ApiModelProperty("当前用户token")
    String token;

    @ApiModelProperty("文章id")
    long articleId;

    @ApiModelProperty("作者id")
    long authorId;
}


