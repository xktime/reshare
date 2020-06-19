package com.xktime.model.article.dtos;

import lombok.Data;

@Data
public class LoadArticleDto {

    // 分页size
    int size;
    //当前页码
    int page;
    //该页第一条数据在数据库的下标
    int pageStartIndex;
    //加载类型
    String loadArticleType;
    // 数据范围，比如频道ID
    String tag;

}
