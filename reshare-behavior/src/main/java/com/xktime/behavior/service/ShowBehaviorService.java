package com.xktime.behavior.service;

import com.xktime.model.article.pojos.Article;
import com.xktime.model.common.dtos.ResponseResult;

import java.util.List;

public interface ShowBehaviorService {
    ResponseResult saveShowBehavior(List<Article> list);
}
