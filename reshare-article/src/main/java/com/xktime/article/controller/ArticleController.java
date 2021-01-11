package com.xktime.article.controller;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import com.xktime.model.pojo.account.dto.PublishDto;
import com.xktime.model.pojo.article.entity.OriginalArticle;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.templet.RestfulTemplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestfulTemplet restfulTemplet;

    @Autowired
    OriginalArticleServiceImpl originalBaseArticleService;

    @Autowired
    ArticleServiceImpl articleService;


    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = restfulTemplet.getUserByToken(restTemplate, dto.getToken());
        if (author == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT.getCode(), HttpCodeEnum.NOT_FIND_ACCOUNT.getErrorMessage());
        }
        OriginalArticle article = new OriginalArticle();
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setPublishTime(new Date());
        article.setAuthorId(author.getUserId());
        article.setAuthorName(author.getUserName());
        originalBaseArticleService.save(article);
        //todo 插入VerifyArticle时设置文章url
        //todo 符合某项规则直接 articleService.save(article)
        return result;
    }
}
