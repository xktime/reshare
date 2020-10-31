package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.pojos.OriginalArticle;
import com.xktime.model.mappers.article.ArticleMapper;
import com.xktime.model.mappers.article.OriginalArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("OriginalArticle")
public class OriginalBaseArticleServiceImpl implements BaseArticleService<OriginalArticle>, BaseAuditable {

    @Autowired
    OriginalArticleMapper originalArticleMapper;

    @Autowired
    ArticleMapper articleMapper;

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
    public List<OriginalArticle> loadArticles(LoadDto dto) {
        return originalArticleMapper.load(dto);
    }

    @Override
    public void verify(VerifyDto dto) {
        originalArticleMapper.verify(dto);
        //todo 插入article数据库
    }
}
