package com.xktime.apis.app;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("reshare")
public class ArticleController {

    @Autowired
    RestTemplate restTemplate;

    private static final String ARTICLE_REST_URL_PREFIX = "http://ARTICLE";

    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        ResponseResult<List<VerifyArticleDto>> responseResult = new ResponseResult<>();
        try {
            responseResult.ok(restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/load/default",
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
}
