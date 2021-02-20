package com.xktime.model.pojo.article.entity;

import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import lombok.Data;


/**
 * 平台原创文章
 */
@Data
public class OriginalArticle extends BaseVerifyArticle {
    private long authorId;

    public SimpleArticleDto toSimpleArticleDto() {
        SimpleArticleDto dto = new SimpleArticleDto();
        dto.setChannelName(channelName);
        dto.setId(id);
        dto.setPublishTime(publishTime);
        dto.setTitle(title);
        return dto;
    }
}
