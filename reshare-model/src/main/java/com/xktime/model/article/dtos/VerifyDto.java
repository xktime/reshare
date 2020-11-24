package com.xktime.model.article.dtos;

import lombok.Data;

@Data
public class VerifyDto {
    long articleId;
    int status;
    long bindId;
    String type;
}
