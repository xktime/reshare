package com.xktime.model.article.dtos;

import com.xktime.model.user.pojos.AppUser;
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
    //当前用户token
    String token;
    //当前用户
    AppUser user;
    // 数据范围，比如频道ID
    String tag;

    public int getPageStartIndex() {
        return Math.max((this.getPage() - 1) * this.getSize(), 0);
    }

    //pageStartIndex通过计算，不允许赋值
    private void setPageStartIndex() {
    }
}
