package com.xktime.article.controller;

import com.xktime.article.service.ArticleService;
import com.xktime.model.account.dtos.PublishDto;
import com.xktime.model.article.pojos.OriginalArticle;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = dto.getUser();
        if (author == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT.getCode(), HttpCodeEnum.NOT_FIND_ACCOUNT.getErrorMessage());
        }
        OriginalArticle article = new OriginalArticle();
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setPublishTime(new Date());
        article.setAuthorId(author.getUserId());
        article.setAuthorName(author.getUserName());
        articleService.saveOriginalArticle(article);
        return result;
    }
}
