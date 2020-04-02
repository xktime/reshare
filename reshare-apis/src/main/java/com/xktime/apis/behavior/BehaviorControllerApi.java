package com.xktime.apis.behavior;

import com.xktime.model.article.pojos.Article;
import com.xktime.model.common.dtos.ResponseResult;

import java.util.List;

public interface BehaviorControllerApi {

    /**
     * 保存用户点击文章行为
     *
     * @param list
     * @return
     */
    ResponseResult saveShowBehavior(List<Article> list);
}
