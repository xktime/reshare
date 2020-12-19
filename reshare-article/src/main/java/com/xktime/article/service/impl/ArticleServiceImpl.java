package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dtos.s2c.SimpleArticleDto;
import com.xktime.model.article.dtos.s2c.VerifyArticleDto;
import com.xktime.model.article.pos.Article;
import com.xktime.model.mappers.article.ArticleMapper;
import com.xktime.model.util.TransferUtils;
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
    public List<Article> loadArticles(LoadDo loadDo) {
        return articleMapper.load(loadDo);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadDo loadDo) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<Article> articles = loadArticles(loadDo);
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                VerifyArticleDto verifyArticle = TransferUtils.toVerifyArticleDto(article);
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadDo loadDo) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<Article> articles = loadArticles(loadDo);
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                SimpleArticleDto simpleArticle = TransferUtils.toSimpleArticleDto(article);
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    public void removeById(@Param("id") long id) {
        articleMapper.removeById(id);
    }

}
