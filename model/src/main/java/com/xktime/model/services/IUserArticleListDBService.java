package com.xktime.model.services;

import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.pojo.user.entity.UserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract class IUserArticleListDBService {
    abstract public List<UserArticleList> loadArticleIdListByUser(@Param("user") AppUser appUser, @Param("dto") LoadDto dto);
}
