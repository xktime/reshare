package com.xktime.model.pojo.comment.dto.c2s;

import com.xktime.model.pojo.comment.query.LoadQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求加载评论dto")
public class LoadDto {

    @ApiModelProperty("分页size")
    int size;

    @ApiModelProperty("当前页码")
    int page;

    @ApiModelProperty("评论类型")
    int loadCommentType;

    @ApiModelProperty("文章id")
    long bindId;

    @ApiModelProperty("作者id")
    long authorId;

    public LoadQuery toQuery() {
        LoadQuery loadQuery = new LoadQuery();
        loadQuery.setAuthorId(authorId);
        loadQuery.setBindId(bindId);
        loadQuery.setLoadCommentType(String.valueOf(loadCommentType));
        loadQuery.setPage(page);
        loadQuery.setSize(size);
        return loadQuery;
    }

}
