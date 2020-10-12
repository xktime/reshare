package com.xktime.apis.app;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import org.apache.commons.lang.StringUtils;
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

    private static final String USER_REST_URL_PREFIX = "http://USER";

    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        ResponseResult<List<VerifyArticleDto>> result = new ResponseResult<>();
        try {
            if (StringUtils.isEmpty(dto.getToken())) {
                ResponseResult<AppUser> user = restTemplate.exchange(
                        USER_REST_URL_PREFIX + "/api/getUserByToken",
                        HttpMethod.POST,
                        new HttpEntity<>(dto.getToken()),
                        new ParameterizedTypeReference<ResponseResult<AppUser>>() {
                        }).getBody();
                if (user.getCode() == HttpCodeEnum.SUCCESS.getCode()) {
                    dto.setUser(user.getData());
                } else {
                    result.error(HttpCodeEnum.NOT_FIND_ACCOUNT.getCode(), HttpCodeEnum.NOT_FIND_ACCOUNT.getErrorMessage());
                    return result;
                }
                result.ok(restTemplate.exchange(
                        ARTICLE_REST_URL_PREFIX + "/load/byAccount",
                        HttpMethod.POST,
                        new HttpEntity<>(dto),
                        new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                        }).getBody());
            } else {
                result.ok(restTemplate.exchange(
                        ARTICLE_REST_URL_PREFIX + "/load/byType",
                        HttpMethod.POST,
                        new HttpEntity<>(dto),
                        new ParameterizedTypeReference<List<VerifyArticleDto>>() {
                        }).getBody());
            }
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
        return result;
    }
}
