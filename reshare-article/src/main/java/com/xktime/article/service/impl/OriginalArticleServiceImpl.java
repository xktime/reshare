package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.OriginalArticle;
import com.xktime.model.mappers.article.OriginalArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class OriginalArticleServiceImpl implements ArticleService<OriginalArticle> {

    @Autowired
    OriginalArticleMapper originalArticleMapper;

    @Override
    public void save(OriginalArticle article) {
        originalArticleMapper.saveArticle(article);
    }

    @Override
    public void saveArticles(Collection<OriginalArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            return;
        }
        for (OriginalArticle article : articles) {
            save(article);
        }
    }

    @Override
    public List<OriginalArticle> loadArticles(LoadArticleDto dto) {
        return originalArticleMapper.load(dto);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticleDtoList(LoadArticleDto dto) {
        throw new NoSuchMethodError();
    }

    @Override
    public void verify(VerifyDto dto) {
        throw new NoSuchMethodError();
    }
}
