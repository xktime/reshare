package com.xktime.model.article.dos;

import lombok.Data;

@Data
public class VerifyDo {
    long articleId;
    int status;
    long bindId;
    String type;
}
