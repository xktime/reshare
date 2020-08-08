package com.xktime.model.mappers.user;

import com.xktime.model.user.pojos.AppUser;
import org.apache.ibatis.annotations.Param;

public interface AppUserMapper {
    void saveUser(AppUser user);

    AppUser queryByAccount(@Param("account") String account);
}
