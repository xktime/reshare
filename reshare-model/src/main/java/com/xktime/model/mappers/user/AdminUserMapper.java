package com.xktime.model.mappers.user;

import com.xktime.model.user.pos.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    void saveUser(AdminUser user);

    AdminUser queryByAccount(@Param("account")String account);
}
