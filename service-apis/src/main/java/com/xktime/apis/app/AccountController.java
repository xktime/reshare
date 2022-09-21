package com.xktime.apis.app;

import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.apis.templet.RestfulTemplet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "app用户相关接口")
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    RestfulTemplet restfulTemplet;

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
            return restfulTemplet.registerApp(dto);
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL);
            return result;
        }
    }

}
