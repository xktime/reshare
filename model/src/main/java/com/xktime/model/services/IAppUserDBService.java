package com.xktime.model.services;

import com.xktime.model.pojo.user.entity.AppUser;
import org.apache.ibatis.annotations.Param;

public abstract class IAppUserDBService {
    abstract public void saveUser(AppUser user);

    abstract public AppUser queryByAccount(@Param("account") String account);

    abstract public AppUser queryByUserId(@Param("userId") long userId);
}
