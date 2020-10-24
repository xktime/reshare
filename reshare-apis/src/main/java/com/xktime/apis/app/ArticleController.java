package com.xktime.apis.app;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    @PostMapping("load")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        ResponseResult<List<VerifyArticleDto>> result = new ResponseResult<>();
        try {
            if (ArticleTypeEnum.ORIGINAL_ARTICLE.getDec().equals(dto.getLoadArticleType())
                    && !StringUtils.isEmpty(dto.getToken())) {
                AppUser user = restTemplate.exchange(
                        USER_REST_URL_PREFIX + "/api/getUserByToken",
                        HttpMethod.POST,
                        new HttpEntity<>(dto.getToken()),
                        new ParameterizedTypeReference<AppUser>() {
                        }).getBody();
                if (user != null) {
                    dto.setUser(user);
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
