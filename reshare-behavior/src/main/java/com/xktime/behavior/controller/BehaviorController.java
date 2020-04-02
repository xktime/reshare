package com.xktime.behavior.controller;

import com.xktime.apis.behavior.BehaviorControllerApi;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.common.dtos.ResponseResult;

import java.util.List;

public class BehaviorController implements BehaviorControllerApi {
    @Override
    public ResponseResult saveShowBehavior(List<Article> list) {
        return null;
    }
}
