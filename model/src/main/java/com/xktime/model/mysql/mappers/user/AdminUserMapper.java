package com.xktime.model.mysql.mappers.user;

import com.xktime.model.pojo.user.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminUserMapper {
    void saveUser(AdminUser user);

    AdminUser queryByAccount(@Param("account") String account);
}
