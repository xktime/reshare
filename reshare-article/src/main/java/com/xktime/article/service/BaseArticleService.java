package com.xktime.article.service;

import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.LoadedArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface BaseArticleService<T> {
    long save(T article);

    void saveArticles(Collection<T> articles);

    long update(T article);

    T findById(long id);

    List<T> loadArticles(LoadDto dto);

    default List<VerifyArticleDto> loadVerifyArticleDtoListNotNull(LoadDto dto) {
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
                //url为空表示不是爬取的文章
                if (StringUtils.isEmpty(verifyArticle.getUrl())) {
                    String url = "/#/" + verifyArticle.getId();
                    verifyArticle.setUrl(url);
                }
                verifyArticleList.add(verifyArticle);
            }
        }
        return verifyArticleList;
    }

    default List<LoadedArticleDto> loadArticleDtoListNotNull(LoadDto dto) {
        List<LoadedArticleDto> loadedArticleList = new ArrayList<>();
        if (dto == null || dto.getSize() <= 0 || StringUtils.isEmpty(dto.getLoadArticleType())) {
            return loadedArticleList;
        }
        List articles = loadArticles(dto);
        //格式转换
        if (articles != null && !articles.isEmpty()) {
            for (Object article : articles) {
                LoadedArticleDto verifyArticle = new LoadedArticleDto();
                BeanUtils.copyProperties(article, verifyArticle);
                //url为空表示不是爬取的文章
                if (StringUtils.isEmpty(verifyArticle.getUrl())) {
                    String url = "/#/" + verifyArticle.getId();
                    verifyArticle.setUrl(url);
                }
                loadedArticleList.add(verifyArticle);
            }
        }
        return loadedArticleList;
    }

}
