package com.xktime.model.article.dtos;

import lombok.Data;

@Data
public class VerifyDto {
    int articleId;
    int status;
    String type;
}
