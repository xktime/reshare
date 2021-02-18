package com.xktime.model.pojo.article.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVerifyArticle implements Serializable {
    private long id;
    private String title;
    private String content;
    private String authorName;
    private int status;
    private long bindId;

    public Article toArticle() {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthorName(authorName);
        article.setStatus(status);
        return article;
    }

    //todo 子类继承toSimpleArticleDto和toVerifyArticleDto
}
