package com.xktime.article.service;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;

public interface ArticleService {
    ResponseResult load(ArticleHomeDto dto);
}
