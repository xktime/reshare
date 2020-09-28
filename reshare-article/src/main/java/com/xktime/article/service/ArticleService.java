package com.xktime.article.service;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;

import java.util.Collection;
import java.util.List;

public interface ArticleService<T> {
    void save(T article);

    void saveArticles(Collection<T> articles);

    List<T> loadArticles(LoadArticleDto dto);

    List<VerifyArticleDto> loadVerifyArticleDtoList(LoadArticleDto dto);

    void verify(VerifyDto dto);

}
