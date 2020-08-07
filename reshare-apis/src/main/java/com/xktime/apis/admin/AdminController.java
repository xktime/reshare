package com.xktime.apis.admin;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.LoginDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RestTemplate restTemplate;

    private static final String ARTICLE_REST_URL_PREFIX = "http://ARTICLE";

    private static final String USER_REST_URL_PREFIX = "http://USER";

    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        ResponseResult<List<VerifyArticleDto>> responseResult = new ResponseResult<>();
        try {
            responseResult.ok(restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/load/loadVerifyArticle",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                    }).getBody());
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }

    @GetMapping("verify")
    public ResponseResult verify(VerifyDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restTemplate.put(ARTICLE_REST_URL_PREFIX + "/admin/verify", dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }

    @PostMapping("login")
    public ResponseResult login(LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult = restTemplate.exchange(
                    USER_REST_URL_PREFIX + "/login",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<ResponseResult>() {
                    }).getBody();
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }
}
