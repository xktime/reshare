package com.xktime.model.article.dtos;

import lombok.Data;
import java.util.Date;

@Data
public class ArticleHomeDto {

    // 分页size
    int size;
    //当前页码
    int page;
    // 数据范围，比如频道ID
    String tag;

}
