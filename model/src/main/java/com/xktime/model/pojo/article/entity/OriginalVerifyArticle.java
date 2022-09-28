package com.xktime.model.pojo.article.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 平台原创文章
 */
@Data
@Document(collection = "OriginalVerifyArticle")
public class OriginalVerifyArticle extends BaseVerifyArticle {
    private long authorId;

}
