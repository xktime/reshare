package com.xktime.user.service;

import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.utils.util.CodeUtil;
import org.apache.ibatis.annotations.Param;


public interface BaseUserService<T> {

    void save(T user);

    T queryByAccount(@Param("account") String account);

    T queryByUserId(long userId);

    default String getTokenByAccount(@Param("account") String account) {
        return CodeUtil.encryptBase64(account, CodeConstant.LOGIN_TOKEN_BASE64_KEY);
    }

    default String getAccountByToken(@Param("token") String token) {
        return CodeUtil.decryptBase64(token, CodeConstant.LOGIN_TOKEN_BASE64_KEY);
    }
}
