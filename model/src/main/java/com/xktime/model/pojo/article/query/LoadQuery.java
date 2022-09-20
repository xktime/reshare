package com.xktime.model.pojo.article.query;

import com.xktime.model.pojo.common.constant.GlobalConstant;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class LoadQuery {
    int size;//分页size

    int page;//当前页码

    String token;//当前用户token

    int pageStartIndex;//该页第一条数据在数据库的下标

    Timestamp lastTime;

    public int getSize() {
        return size == 0 ? GlobalConstant.DEFAULT_LOAD_ARTICLE_SIZE : size;
    }

    public int getPage() {
        return page == 0 ? 1 : page;
    }

    public int getPageStartIndex() {
        return Math.max((this.getPage() - 1) * this.getSize(), 0);
    }

    //pageStartIndex通过计算，不允许赋值
    private void setPageStartIndex() {
    }
}


