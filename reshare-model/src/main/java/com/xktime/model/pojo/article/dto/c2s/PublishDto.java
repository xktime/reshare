package com.xktime.model.pojo.article.dto.c2s;

import com.xktime.model.pojo.article.entity.OriginalArticle;
import com.xktime.model.pojo.user.entity.AppUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "请求发布文章dto")
public class PublishDto {
    @ApiModelProperty("文章标题")
    String title;
    @ApiModelProperty("文章内容")
    String content;
    @ApiModelProperty("当前账户token")
    String token;

    //todo 把toOriginalArticle放在PublishDto似乎不太合适
    public OriginalArticle toOriginalArticle(AppUser author) {
        OriginalArticle article = new OriginalArticle();
        article.setContent(content);
        article.setTitle(title);
        article.setPublishTime(new Date());
        article.setAuthorId(author.getUserId());
        article.setAuthorName(author.getUserName());
        return article;
    }
}
