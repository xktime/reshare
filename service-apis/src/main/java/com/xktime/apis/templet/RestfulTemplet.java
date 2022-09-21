package com.xktime.apis.templet;

import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisKeyUtil;
import com.xktime.model.redis.RedisUtil;
import com.xktime.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//todo url分类？
//todo 模板放在model内好像不太合适
//todo 或许改成Rpc？
//todo util包导入
@Service
public class RestfulTemplet {

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    @Value("${restful.url.comment}")
    private String COMMENT_REST_URL_PREFIX;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisUtil redisUtil;


    public AppUser getUserByToken(String token) {
        String key = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token);
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, key);
        if (user == null) {
            user = restTemplate.exchange(
                    USER_REST_URL_PREFIX + "/api/getUserByToken",
                    HttpMethod.POST,
                    new HttpEntity<>(token),
                    new ParameterizedTypeReference<AppUser>() {
                    }).getBody();
            redisUtil.mapSet(RedisCommonKey.APP_USR, key, user);
        }
        return user;
    }

    public AppUser getUserByAccount(String account) {
        String token = CodeUtil.encryptBase64(account, CodeConstant.LOGIN_TOKEN_BASE64_KEY);
        return getUserByToken(token);
    }

    public AppUser getUserByUserId(long userId) {
        String key = RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, userId);
        AppUser user = redisUtil.mapGet(RedisCommonKey.APP_USR, key);
        if (user == null) {
            user = restTemplate.exchange(
                    USER_REST_URL_PREFIX + "/api/getUserByUserId",
                    HttpMethod.POST,
                    new HttpEntity<>(userId),
                    new ParameterizedTypeReference<AppUser>() {
                    }).getBody();
            redisUtil.mapSet(RedisCommonKey.APP_USR, key, user);
        }
        return user;
    }

    public List<CommentDto> getComments(com.xktime.model.pojo.comment.dto.c2s.LoadDto loadDto) {
        return restTemplate.exchange(
                COMMENT_REST_URL_PREFIX + "/load/comment",
                HttpMethod.POST,
                new HttpEntity<>(loadDto),
                new ParameterizedTypeReference<List<CommentDto>>() {
                }).getBody();
    }

    public List<VerifyArticleDto> loadArticle(LoadDto dto) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/verifyArticles",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                }).getBody();
    }

    public void verifyArticle(VerifyDto dto) {
        restTemplate.put(ARTICLE_REST_URL_PREFIX + "/admin/verify", dto);
    }

    public ResponseResult loginAdmin(LoginDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/login/admin",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult publishArticle(com.xktime.model.pojo.article.dto.c2s.PublishDto dto) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/article/publish",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult publishComment(com.xktime.model.pojo.comment.dto.c2s.PublishDto dto) {
        return restTemplate.exchange(
                COMMENT_REST_URL_PREFIX + "/comment/publish",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public ResponseResult registerApp(RegisterDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/user/register",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }

    public List<SimpleArticleDto> loadSimpleArticle(LoadDto dto) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/simpleArticles",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<List<SimpleArticleDto>>() {
                }).getBody();
    }

    public ArticleDetailsDto loadArticleDetails(String articleId) {
        return restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/articleDetails",
                HttpMethod.POST,
                new HttpEntity<>(articleId),
                new ParameterizedTypeReference<ArticleDetailsDto>() {
                }).getBody();
    }

    public ResponseResult commonLoginApp(LoginDto dto) {
        return restTemplate.exchange(
                USER_REST_URL_PREFIX + "/login/app",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult>() {
                }).getBody();
    }
}
