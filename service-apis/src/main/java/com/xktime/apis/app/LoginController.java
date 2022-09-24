package com.xktime.apis.app;

import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.apis.templet.RestfulTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "app用户登录相关接口")
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    RestfulTemplate restfulTemplet;

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
            responseResult = restfulTemplet.commonLoginApp(dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL);
            return responseResult;
        }
        return responseResult;
    }

    //todo qq号以及github等平台认证登录

}
