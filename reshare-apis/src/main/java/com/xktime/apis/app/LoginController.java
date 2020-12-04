package com.xktime.apis.app;

import com.xktime.model.account.dtos.LoginDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
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

@Api(tags = "app用户登录相关接口")
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${restful.url.user}")
    private String USER_REST_URL_PREFIX;

    @Value("${restful.url.article}")
    private String ARTICLE_REST_URL_PREFIX;

    /**
     * 正常使用账号密码登录
     *
     * @param dto
     * @return
     */
    @ApiOperation("使用账号密码登录")
    @PostMapping("common")
    public ResponseResult commonLogin(LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult = restTemplate.exchange(
                    USER_REST_URL_PREFIX + "/login/app",
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

    //todo qq号以及github等平台认证登录

}
