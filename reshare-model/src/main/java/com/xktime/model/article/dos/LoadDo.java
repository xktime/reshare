package com.xktime.model.article.dos;

import lombok.Data;

@Data
public class LoadDo {
    //todo dto转do格式转换需要重构
    int size;//分页size

    int page;//当前页码

    String loadArticleType;//加载类型

    String token;//当前用户token

    String tag;//数据范围，比如频道ID

    int pageStartIndex;//该页第一条数据在数据库的下标

    public int getPageStartIndex() {
        return Math.max((this.getPage() - 1) * this.getSize(), 0);
    }

    //pageStartIndex通过计算，不允许赋值
    private void setPageStartIndex() {
    }
}


