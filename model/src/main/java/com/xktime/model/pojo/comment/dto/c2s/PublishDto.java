package com.xktime.model.pojo.comment.dto.c2s;

import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.user.entity.AppUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "请求发布评论dto")
public class PublishDto {
    @ApiModelProperty("绑定ID(文章为article_id)")
    private long bindId;
    @ApiModelProperty("评论类型")
    private int type;
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("当前账户token")
    private String token;

    public Comment toComment(AppUser author) {
        Comment comment = new Comment();
        comment.setAuthorId(author.getId());
        comment.setBindId(bindId);
        comment.setType(type);
        comment.setContent(content);
        comment.setPublishTime(System.currentTimeMillis());
        return comment;
    }
}
