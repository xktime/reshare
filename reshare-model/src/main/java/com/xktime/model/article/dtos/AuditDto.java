package com.xktime.model.article.dtos;

import lombok.Data;

@Data
public class AuditDto {
    int articleId;
    int status;
    int type;
}
