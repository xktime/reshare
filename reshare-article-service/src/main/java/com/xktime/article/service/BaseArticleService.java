package com.xktime.article.service;

import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.query.LoadQuery;

import java.util.Collection;
import java.util.List;

public interface BaseArticleService<T> {
    void save(T article);

    void saveArticles(Collection<T> articles);

    void update(T article);

    T findById(long id);

    List<T> loadArticles(LoadQuery loadQuery);

    List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery);

}
