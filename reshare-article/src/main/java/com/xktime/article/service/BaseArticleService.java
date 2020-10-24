package com.xktime.article.service;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface BaseArticleService<T> {
    void save(T article);

    void saveArticles(Collection<T> articles);

    List<T> loadArticles(LoadArticleDto dto);

    void verify(VerifyDto dto);

    default List<VerifyArticleDto> loadVerifyArticleDtoListNotNull(LoadArticleDto dto) {
        List<VerifyArticleDto> verifyArticleList = new ArrayList<>();
        if (dto == null || dto.getSize() <= 0 || StringUtils.isEmpty(dto.getLoadArticleType())) {
            return verifyArticleList;
        }
        List articles = loadArticles(dto);
        //格式转换
        if (articles != null && !articles.isEmpty()) {
            for (Object article : articles) {
                VerifyArticleDto verifyArticle = new VerifyArticleDto();
                BeanUtils.copyProperties(article, verifyArticle);
                verifyArticleList.add(verifyArticle);
            }
        }
        return verifyArticleList;
    }

}
