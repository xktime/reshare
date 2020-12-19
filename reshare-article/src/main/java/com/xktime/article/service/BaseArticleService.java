package com.xktime.article.service;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dtos.s2c.SimpleArticleDto;
import com.xktime.model.article.dtos.s2c.VerifyArticleDto;

import java.util.Collection;
import java.util.List;

public interface BaseArticleService<T> {
    void save(T article);

    void saveArticles(Collection<T> articles);

    void update(T article);

    T findById(long id);

    List<T> loadArticles(LoadDo loadDo);

    List<VerifyArticleDto> loadVerifyArticles(LoadDo loadDo);

    List<SimpleArticleDto> loadSimpleArticles(LoadDo loadDo);

}
