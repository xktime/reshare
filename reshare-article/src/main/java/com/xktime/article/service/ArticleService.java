package com.xktime.article.service;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;

public interface ArticleService {
    ResponseResult load(ArticleHomeDto dto, UserStatusEnum status);
}
