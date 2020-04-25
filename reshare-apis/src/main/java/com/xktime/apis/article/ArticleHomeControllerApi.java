package com.xktime.apis.article;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;

public interface ArticleHomeControllerApi {

    ResponseResult load(ArticleHomeDto dto);
}
