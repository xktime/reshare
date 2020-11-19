package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.mappers.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Article findById(int id) {
        return null;
    }

    @Override
    public List<Article> loadArticles(LoadDto dto) {
        return articleMapper.load(dto);
    }

}
