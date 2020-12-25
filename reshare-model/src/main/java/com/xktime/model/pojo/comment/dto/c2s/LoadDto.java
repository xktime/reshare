package com.xktime.model.pojo.comment.dto.c2s;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求加载评论dto")
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("评论类型")
    String loadCommentType;

    @ApiModelProperty("文章id")
    long bindId;

    @ApiModelProperty("作者id")
    long authorId;

}
