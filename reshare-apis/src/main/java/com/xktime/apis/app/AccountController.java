package com.xktime.apis.app;

import com.xktime.model.pojo.account.dto.PublishDto;
import com.xktime.model.pojo.account.dto.RegisterDto;
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

@Api(tags = "app用户相关接口")
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    /**
     * 发布文章
     *
     * @param dto
     * @return
     */
    @ApiOperation("发布")
    @PostMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        try {
            return restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/article/publish",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<ResponseResult>() {
                    }).getBody();
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
    }

    /**
     * 注册账户
     *
     * @param dto
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("register")
    public ResponseResult register(RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        try {
            return restTemplate.exchange(
                    USER_REST_URL_PREFIX + "/user/register",
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    new ParameterizedTypeReference<ResponseResult>() {
                    }).getBody();
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
    }

}
