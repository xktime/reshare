package com.xktime.article.service;

import com.xktime.model.article.dtos.VerifyDto;

public interface BaseAuditable {

    //todo 插入article数据库
    void verify(VerifyDto dto);
}
