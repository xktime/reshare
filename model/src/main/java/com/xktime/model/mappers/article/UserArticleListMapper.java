package com.xktime.model.mappers.article;

import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.pojo.user.entity.UserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserArticleListMapper {
    List<UserArticleList> loadArticleIdListByUser(@Param("user") AppUser appUser, @Param("dto") LoadDto dto);
}
