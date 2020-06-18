package com.xktime.article.service;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;

public interface ArticleService {
    ResponseResult load(LoadArticleDto dto, UserStatusEnum status);

}
