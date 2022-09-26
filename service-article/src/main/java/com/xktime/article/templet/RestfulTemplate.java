package com.xktime.article.templet;

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

//todo 整理
@Service
public class RestfulTemplate {

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

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

}
