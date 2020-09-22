package com.xktime.apis.app;

import com.xktime.model.account.dtos.PublishDto;
import com.xktime.model.account.dtos.RegisterDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.HttpCodeEnum;
import com.xktime.model.user.pojos.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    RestTemplate restTemplate;

    private static final String ARTICLE_REST_URL_PREFIX = "http://ARTICLE";

    private static final String USER_REST_URL_PREFIX = "http://USER";

    /**
     * 发布文章
     *
     * @param dto
     * @return
     */
    @PostMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        try {
            //根据token获取用户
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
            return restTemplate.exchange(
                    ARTICLE_REST_URL_PREFIX + "/publish",
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
    @PostMapping("register")
    public ResponseResult register(RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        return result;
    }

}
