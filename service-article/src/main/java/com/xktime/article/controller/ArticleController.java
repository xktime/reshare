package com.xktime.article.controller;

import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.article.templet.RestfulTemplate;
import com.xktime.model.pojo.article.dto.c2s.PublishDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestfulTemplate restfulTemplet;

    @Autowired
    OriginalArticleServiceImpl originalBaseArticleService;

    @Autowired
    VerifiedArticleServiceImpl verifiedArticleService;


    @RequestMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = restfulTemplet.getUserByToken(dto.getToken());
        if (author == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT);
        }
        originalBaseArticleService.save(dto.toOriginalArticle(author));
        //todo 符合某项规则直接 articleService.save(article)
        return result;
    }
}
