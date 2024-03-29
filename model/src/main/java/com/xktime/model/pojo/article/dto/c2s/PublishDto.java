package com.xktime.model.pojo.article.dto.c2s;

import com.xktime.model.pojo.article.entity.OriginalVerifyArticle;
import com.xktime.model.pojo.user.entity.AppUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "请求发布文章dto")
public class PublishDto {
    @ApiModelProperty("文章标题")
    String title;
    @ApiModelProperty("文章内容")
    List<String> content;
    @ApiModelProperty("当前账户token")
    String token;

    public OriginalVerifyArticle toOriginalArticle(AppUser author) {
        OriginalVerifyArticle article = new OriginalVerifyArticle();
        article.setContent(content);
        article.setTitle(title);
        article.setPublishTime(System.currentTimeMillis());
        article.setAuthorId(author.getUserId());
        article.setAuthorName(author.getUserName());
        article.setUrl("/");
        return article;
    }
}
