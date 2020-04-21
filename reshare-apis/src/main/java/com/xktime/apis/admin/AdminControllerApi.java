package com.xktime.apis.admin;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;

public interface AdminControllerApi {
    ResponseResult loadCrawlerArticle(ArticleHomeDto dto);
}
