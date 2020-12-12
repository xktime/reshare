package com.xktime.article.service;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dtos.LoadedArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface BaseArticleService<T> {
    void save(T article);

    void saveArticles(Collection<T> articles);

    void update(T article);

    T findById(long id);

    List<T> loadArticles(LoadDo loadDo);

    default List<VerifyArticleDto> loadVerifyArticleDtoListNotNull(LoadDo loadDo) {
        List<VerifyArticleDto> verifyArticleList = new ArrayList<>();
        if (loadDo == null || loadDo.getSize() <= 0 || StringUtils.isEmpty(loadDo.getLoadArticleType())) {
            return verifyArticleList;
        }
        List articles = loadArticles(loadDo);
        //格式转换
        if (articles != null && !articles.isEmpty()) {
            for (Object article : articles) {
                VerifyArticleDto verifyArticle = new VerifyArticleDto();
                BeanUtils.copyProperties(article, verifyArticle);
                //url为空表示不是爬取的文章
                //todo 修改转换成地址url
                if (StringUtils.isEmpty(verifyArticle.getUrl())) {
                    String url = "/" + verifyArticle.getId();
                    verifyArticle.setUrl(url);
                }
                verifyArticleList.add(verifyArticle);
            }
        }
        return verifyArticleList;
    }

    default List<LoadedArticleDto> loadArticleDtoListNotNull(LoadDo loadDo) {
        List<LoadedArticleDto> loadedArticleList = new ArrayList<>();
        if (loadDo == null || loadDo.getSize() <= 0 || StringUtils.isEmpty(loadDo.getLoadArticleType())) {
            return loadedArticleList;
        }
        List articles = loadArticles(loadDo);
        //格式转换
        if (articles != null && !articles.isEmpty()) {
            for (Object article : articles) {
                LoadedArticleDto verifyArticle = new LoadedArticleDto();
                BeanUtils.copyProperties(article, verifyArticle);
                //url为空表示不是爬取的文章
                if (StringUtils.isEmpty(verifyArticle.getUrl())) {
                    String url = "/" + verifyArticle.getId();
                    verifyArticle.setUrl(url);
                }
                loadedArticleList.add(verifyArticle);
            }
        }
        return loadedArticleList;
    }

}
