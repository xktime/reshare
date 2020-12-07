package com.xktime.article.controller;

import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.service.impl.OriginalBaseArticleServiceImpl;
import com.xktime.model.account.dtos.PublishDto;
import com.xktime.model.article.pos.OriginalArticle;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pos.AppUser;
import com.xktime.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OriginalBaseArticleServiceImpl originalBaseArticleService;

    @Autowired
    ArticleServiceImpl articleService;

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = restTemplate.exchange(
                USER_REST_URL_PREFIX + "/api/getUserByToken",
                HttpMethod.POST,
                new HttpEntity<>(dto.getToken()),
                new ParameterizedTypeReference<AppUser>() {
                }).getBody();
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
        //todo 符合某项规则直接 articleService.save(article)
        return result;
    }
}
