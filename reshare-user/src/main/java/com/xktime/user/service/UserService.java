package com.xktime.user.service;

import com.xktime.model.user.pojos.AdminUser;
import com.xktime.model.user.pojos.AppUser;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    void saveAdminUser(AdminUser user);

    void saveAppUser(AppUser user);

    AdminUser queryAdminUserByAccount(@Param("account")String account);

    AppUser queryAppUserByAccount(@Param("account")String account);

    String getAccountByToken(@Param("token")String token);

    String getTokenByAccount(@Param("account")String account);
}
