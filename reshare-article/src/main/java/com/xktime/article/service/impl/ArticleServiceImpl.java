package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.mappers.article.ArticleMapper;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.article.query.LoadQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 首页显示文章
 */
@Service("Article")
public class ArticleServiceImpl implements BaseArticleService<Article> {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void save(Article article) {
        articleMapper.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<Article> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (Article article : articles) {
            save(article);
        }
    }

    @Override
    public void update(Article article) {
        //todo 实现
    }

    @Override
    public Article findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> loadArticles(LoadQuery loadQuery) {
        return articleMapper.load(loadQuery);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadQuery loadQuery) {
        //todo article是否不需要实现该方法，如果是把方法实现整理到父类去
//        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
//        List<Article> articles = loadArticles(loadQuery);
//        if (articles != null && !articles.isEmpty()) {
//            for (Article article : articles) {
//                VerifyArticleDto verifyArticle = article.toVerifyArticleDto();
//                verifyArticles.add(verifyArticle);
//            }
//        }
//        return verifyArticles;
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<Article> articles = loadArticles(loadQuery);
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                SimpleArticleDto simpleArticle = article.toSimpleArticleDto();
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    public void removeById(@Param("id") long id) {
        articleMapper.removeById(id);
    }

}
