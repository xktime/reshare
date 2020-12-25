package com.xktime.model.pojo.article.query;

import lombok.Data;

@Data
public class VerifyQuery {
    long articleId;
    int status;
    long bindId;
    String type;
}
