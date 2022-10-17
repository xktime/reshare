package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Article基类，所有Article实体都需要继承该类
 */
@Data
public class BaseArticle implements Serializable {
    @Id
    protected long id;
    @Field
    protected String title;
    protected String content;
    protected long publishTime;
}
