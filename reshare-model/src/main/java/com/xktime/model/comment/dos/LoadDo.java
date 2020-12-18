package com.xktime.model.comment.dos;

import com.xktime.model.common.constant.GlobalConstant;
import lombok.Data;

@Data
public class LoadDo {
    int size;//分页size

    int page;//当前页码

    String loadCommentType;//加载类型

    int pageStartIndex;//该页第一条数据在数据库的下标

    long authorId;//作者id

    long bindId;//所绑定的id

    public int getSize() {
        return size == 0 ? GlobalConstant.DEFAULT_LOAD_COMMENT_SIZE : size;
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


