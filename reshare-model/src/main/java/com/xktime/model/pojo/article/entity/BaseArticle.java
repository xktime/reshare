package com.xktime.model.pojo.article.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Article基类，所有Article实体都需要继承该类
 */
@Data
public class BaseArticle implements Serializable {
    @Id
    protected long id;
    protected String title;
    protected String content;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date publishTime;
}
