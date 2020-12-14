package com.xktime.model.mappers.article;

import com.xktime.model.article.dtos.c2s.LoadDto;
import com.xktime.model.user.pos.AppUser;
import com.xktime.model.user.pos.UserArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserArticleListMapper {
    List<UserArticleList> loadArticleIdListByUser(@Param("user") AppUser appUser, @Param("dto") LoadDto dto);
}
