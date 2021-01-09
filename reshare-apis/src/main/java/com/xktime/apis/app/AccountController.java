package com.xktime.apis.app;

import com.xktime.model.pojo.account.dto.PublishDto;
import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.templet.RestfulTemplet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RestfulTemplet restfulTemplet;

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
            return restfulTemplet.publishArticle(restTemplate, dto);
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
            return restfulTemplet.registerApp(restTemplate, dto);
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
    }

}
