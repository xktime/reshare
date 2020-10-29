package com.xktime.article.service;

import com.xktime.model.article.dtos.VerifyDto;

public interface BaseAuditable {

    void verify(VerifyDto dto);
}
