package com.xktime.apis.app;

import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "app文章相关接口")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    @ApiOperation("加载文章")
    @PostMapping("load")
    public ResponseResult loadArticle(LoadDto dto) {
        ResponseResult<List<SimpleArticleDto>> result = new ResponseResult<>();
        try {
            result.ok(restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/load/simpleArticles",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<List<SimpleArticleDto>>() {
                    }).getBody());
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
        return result;
    }

    @ApiOperation("加载文章详情")
    @PostMapping("loadDetails")
    public ResponseResult loadArticleDetails(long articleId) {
        ResponseResult<ArticleDetailsDto> result = new ResponseResult<>();
        try {
            result.ok(restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/load/articleDetails",
                    HttpMethod.POST,
                    new HttpEntity<>(articleId),
                    new ParameterizedTypeReference<ArticleDetailsDto>() {
                    }).getBody());
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
        return result;
    }
}
