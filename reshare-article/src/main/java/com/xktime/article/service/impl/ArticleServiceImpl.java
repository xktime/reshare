package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;
import com.xktime.model.mappers.article.ArticleMapper;
import com.xktime.model.mappers.article.UserArticleListMapper;
import com.xktime.model.user.pojos.User;
import com.xktime.model.user.pojos.UserArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserArticleListMapper userArticleListMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, UserStatusEnum status) {
        if (status == UserStatusEnum.LOGGED) {
            List<Article> articleList = articleMapper.loadArticleListByLocation(dto);
            return new ResponseResult().ok(articleList);
        } else if (status == UserStatusEnum.UNLOGIN) {
            User user = new User();
            List<UserArticleList> userArticleLists = userArticleListMapper.loadArticleIdListByUser(user, dto);
            List<Article> articleList = articleMapper.loadArticleListByIdList(userArticleLists);
        }
        return null;
    }
}
