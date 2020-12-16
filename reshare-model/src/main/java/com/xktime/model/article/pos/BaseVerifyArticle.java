package com.xktime.model.article.pos;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVerifyArticle implements Serializable {
    private long id;
    private String title;
    private String content;
    private String authorName;
    private byte status;
    private long bindId;
}
