package com.xktime.model.comment.dos;

import lombok.Data;

@Data
public class LoadDo {
    int size;//分页size

    int page;//当前页码

    String loadCommentType;//加载类型

    int pageStartIndex;//该页第一条数据在数据库的下标

    long authorId;

    long bindId;

    public int getPageStartIndex() {
        return Math.max((this.getPage() - 1) * this.getSize(), 0);
    }

    //pageStartIndex通过计算，不允许赋值
    private void setPageStartIndex() {
    }
}


