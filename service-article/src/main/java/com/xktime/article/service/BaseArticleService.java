package com.xktime.article.service;

import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.entity.BaseArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.cache.RedisCache;

import java.util.Collection;
import java.util.List;

public abstract class BaseArticleService<T extends BaseArticle> {
    public abstract void save(T article);

    public abstract void saveArticles(Collection<T> articles);

    public abstract void update(T article);

    public abstract T findById(long id);

    public abstract List<T> loadArticles(LoadQuery loadQuery);

    public abstract List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery);

}
