package com.xktime.model.mappers.user;

import com.xktime.model.user.pos.AppUser;
import org.apache.ibatis.annotations.Param;

public interface AppUserMapper {
    void saveUser(AppUser user);

    AppUser queryByAccount(@Param("account") String account);
}
