package com.xktime.model.mappers.article;

import com.xktime.model.user.pojos.User;
import com.xktime.model.user.pojos.UserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserArticleListMapper {
    List<UserArticleList> loadArticleIdListByUser(@Param("user") User user);
}
