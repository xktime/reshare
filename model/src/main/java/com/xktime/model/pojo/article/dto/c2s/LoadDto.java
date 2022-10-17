package com.xktime.model.pojo.article.dto.c2s;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xktime.model.pojo.article.query.LoadQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@ApiModel(description = "请求加载文章dto")
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("加载类型 爬取文章:1;原创文章:2;用户推荐文章:3")
    int loadArticleType;

    @ApiModelProperty("当前用户token")
    String token;

    @ApiModelProperty("文章id")
    long articleId;

    @ApiModelProperty("作者id")
    long authorId;

    @ApiModelProperty("上次加载时间")
    private long lastTime;

    public LoadQuery toQuery() {
        LoadQuery loadQuery = new LoadQuery();
        if (size == 0) {
            //todo 注入方式需要修改
            loadQuery.setPage(10);
        } else {
            loadQuery.setSize(size);
        }
        loadQuery.setPage(page);
        loadQuery.setToken(token);
        loadQuery.setLastTime(lastTime);
        return loadQuery;
    }
}


