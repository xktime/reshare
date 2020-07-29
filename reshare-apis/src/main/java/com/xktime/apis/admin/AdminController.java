package com.xktime.apis.admin;

import com.xktime.model.article.dtos.LoginDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.common.dtos.ResponseResult;
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

    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.ok(restTemplate.exchange(
                ARTICLE_REST_URL_PREFIX + "/load/loadVerifyArticle",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                }).getBody());
        return responseResult;
    }

    @GetMapping("verify")
    public void verify(VerifyDto dto) {
        restTemplate.put(ARTICLE_REST_URL_PREFIX + "/admin/verify", dto);
    }

    @PostMapping("login")
    public ResponseResult login(LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        return responseResult;
    }
}
