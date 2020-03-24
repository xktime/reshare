package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public ResponseResult load(ArticleHomeDto dto, UserStatusEnum status) {
        return null;
    }
}
