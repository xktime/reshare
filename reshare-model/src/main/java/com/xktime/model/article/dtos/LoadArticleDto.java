package com.xktime.model.article.dtos;

import lombok.Data;

@Data
public class LoadArticleDto {

    // 分页size
    int size;
    //当前页码
    int page;
    // 数据范围，比如频道ID
    String tag;

}
