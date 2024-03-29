package com.xktime.model.pojo.article.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Article基类，所有Article实体都需要继承该类
 */
@Data
public class BaseArticle implements Serializable {
    @Id
    protected long id;
    @Field
    protected String title;
    //todo 读取有映射问题，mongo里面是list;Spring Converter
    //todo 太大了，要拆出来单独存
    protected List<String> content;
    protected long publishTime;
}
