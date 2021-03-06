package com.xktime.model.templet;

import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.user.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//todo 模块关系整体重构，枚举type分类问题
//todo 模板放在model内不太合适,和TransferUtils一起重构
@Service
public class RestfulTemplet {

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    @Value("${restful.url.comment}")
    private String COMMENT_REST_URL_PREFIX;


    public AppUser getUserByToken(RestTemplate restTemplate, String token) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/api/getUserByToken",
                HttpMethod.POST,
                new HttpEntity<>(token),
                new ParameterizedTypeReference<AppUser>() {
                }).getBody();
    }

    public List<Comment> getComments(RestTemplate restTemplate, com.xktime.model.pojo.comment.dto.c2s.LoadDto loadDto) {
        return restTemplate.exchange(
                COMMENT_REST_URL_PREFIX + "/load/comment",
                HttpMethod.POST,
                new HttpEntity<>(loadDto),
                new ParameterizedTypeReference<List<Comment>>() {
                }).getBody();
    }

    public List<VerifyArticleDto> loadArticle(RestTemplate restTemplate, LoadDto dto) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/verifyArticles",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                }).getBody();
    }

    public void verifyArticle(RestTemplate restTemplate, VerifyDto dto) {
        restTemplate.put(ARTICLE_REST_URL_PREFIX + "/admin/verify", dto);
    }

    public ResponseResult loginAdmin(RestTemplate restTemplate, LoginDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/login/admin",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult publishArticle(RestTemplate restTemplate, com.xktime.model.pojo.article.dto.c2s.PublishDto dto) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/article/publish",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult publishComment(RestTemplate restTemplate, com.xktime.model.pojo.comment.dto.c2s.PublishDto dto) {
        return restTemplate.exchange(
                COMMENT_REST_URL_PREFIX + "/article/publish",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult registerApp(RestTemplate restTemplate, RegisterDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/user/register",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public List<SimpleArticleDto> loadSimpleArticle(RestTemplate restTemplate, LoadDto dto) {
        return restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/load/simpleArticles",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<List<SimpleArticleDto>>() {
                    }).getBody();
    }

    public ArticleDetailsDto loadArticleDetails(RestTemplate restTemplate, String articleId) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/articleDetails",
                HttpMethod.POST,
                new HttpEntity<>(articleId),
                new ParameterizedTypeReference<ArticleDetailsDto>() {
                }).getBody();
    }

    public ResponseResult commonLoginApp(RestTemplate restTemplate, LoginDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/login/app",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }
}
