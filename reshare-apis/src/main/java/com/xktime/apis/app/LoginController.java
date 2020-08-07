package com.xktime.apis.app;

import com.xktime.model.article.dtos.LoginDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

    private static final String USER_REST_URL_PREFIX = "http://USER";

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
