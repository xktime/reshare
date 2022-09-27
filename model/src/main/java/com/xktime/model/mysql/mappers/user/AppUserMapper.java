package com.xktime.model.mysql.mappers.user;

import com.xktime.model.pojo.user.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppUserMapper {
    void saveUser(AppUser user);

    AppUser queryByAccount(@Param("account") String account);

    AppUser queryByUserId(@Param("userId") long userId);
}
