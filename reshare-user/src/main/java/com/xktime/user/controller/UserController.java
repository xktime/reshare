package com.xktime.user.controller;

import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import com.xktime.utils.CodeUtil;
import com.xktime.utils.RedisUtil;
import com.xktime.utils.SnowflakeIdUtil;
import com.xktime.utils.common.RedisCommonKey;
import com.xktime.utils.common.RedisKeyUtil;
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
    SnowflakeIdUtil snowflakeIdUtil;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("register")
    public ResponseResult register(@RequestBody RegisterDto dto) {
        ResponseResult result = new ResponseResult();
        String account = dto.getAccount();
        if (appUserService.queryByAccount(account) != null) {
            result.error(HttpCodeEnum.ACCOUNT_EXISTS);
            return result;
        }
        AppUser user = dto.toUser(snowflakeIdUtil.nextId());
        appUserService.save(user);
        //todo redis缓存统一处理
        String token = CodeUtil.encryptBase64(user.getAccount(), CodeConstant.LOGIN_TOKEN_BASE64_KEY);
        redisUtil.hmSet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_ID, user.getUserId()), user);
        redisUtil.hmSet(RedisCommonKey.APP_USR, RedisKeyUtil.getUniqueKey(RedisCommonKey.USER_TOKEN, token), user);
        return result.ok(token);
    }
}
