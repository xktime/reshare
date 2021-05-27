package com.xktime.model.pojo.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Article基类，所有Article实体都需要继承该类
 */
@Data
public class BaseArticle implements Serializable {
    protected long id;
    protected String title;
    protected String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date publishTime;
}
