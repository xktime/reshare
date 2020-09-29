package com.xktime.user.service;

import com.xktime.model.common.enums.CodeConstants;
import com.xktime.utils.CodeUtil;
import org.apache.ibatis.annotations.Param;


public interface UserService<T> {

    void save(T user);

    T queryByAccount(@Param("account") String account);


    default String getTokenByAccount(@Param("account") String account) {
        return CodeUtil.encryptBase64(account, CodeConstants.LOGIN_TOKEN_BASE64_KEY);
    }

    default String getAccountByToken(@Param("token") String token) {
        return CodeUtil.decryptBase64(token, CodeConstants.LOGIN_TOKEN_BASE64_KEY);
    }
}
