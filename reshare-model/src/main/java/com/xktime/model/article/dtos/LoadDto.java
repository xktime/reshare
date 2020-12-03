package com.xktime.model.article.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("加载类型")
    String loadArticleType;

    @ApiModelProperty("当前用户token")
    String token;

    // 数据范围，比如频道ID
    @ApiModelProperty("数据范围，比如频道ID")
    String tag;

    @ApiModelProperty("该页第一条数据在数据库的下标")
    int pageStartIndex;

    public int getPageStartIndex() {
        return Math.max((this.getPage() - 1) * this.getSize(), 0);
    }

    //pageStartIndex通过计算，不允许赋值
    private void setPageStartIndex() {
    }
}


