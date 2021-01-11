package com.xktime.user.controller;

import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.util.TransferUtils;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import com.xktime.utils.CodeUtil;
import com.xktime.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AppBaseUserServiceImpl appUserService;

    @Autowired
    SnowflakeIdUtil snowflakeId;

    @RequestMapping("register")
    public ResponseResult register(@RequestBody RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        String account = dto.getAccount();
        if (appUserService.queryByAccount(account) != null) {
            result.error(HttpCodeEnum.ACCOUNT_EXISTS.getCode(), HttpCodeEnum.ACCOUNT_EXISTS.getErrorMessage());
            return result;
        }
        AppUser user = TransferUtils.toUser(dto, snowflakeId);
        appUserService.save(user);
        return result.ok(CodeUtil.encryptBase64(dto.getAccount(), CodeConstant.LOGIN_TOKEN_BASE64_KEY));
    }
}
