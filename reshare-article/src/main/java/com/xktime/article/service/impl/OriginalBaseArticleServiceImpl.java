package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.dtos.s2c.SimpleArticleDto;
import com.xktime.model.article.dtos.s2c.VerifyArticleDto;
import com.xktime.model.article.pos.OriginalArticle;
import com.xktime.model.mappers.article.OriginalArticleMapper;
import com.xktime.model.util.TransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("OriginalArticle")
public class OriginalBaseArticleServiceImpl extends BaseAuditable implements BaseArticleService<OriginalArticle> {

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
    public void update(OriginalArticle article) {
        //todo 实现
    }

    @Override
    public OriginalArticle findById(long id) {
        return originalArticleMapper.findById(id);
    }

    @Override
    public List<OriginalArticle> loadArticles(LoadDo loadDo) {
        return originalArticleMapper.load(loadDo);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticles(LoadDo loadDo) {
        List<VerifyArticleDto> verifyArticles = new ArrayList<>();
        List<OriginalArticle> articles = loadArticles(loadDo);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalArticle article : articles) {
                VerifyArticleDto verifyArticle = TransferUtils.toVerifyArticleDto(article);
                verifyArticles.add(verifyArticle);
            }
        }
        return verifyArticles;
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadDo loadDo) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<OriginalArticle> articles = loadArticles(loadDo);
        if (articles != null && !articles.isEmpty()) {
            for (OriginalArticle article : articles) {
                SimpleArticleDto simpleArticle = TransferUtils.toSimpleArticleDto(article);
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    @Override
    public void modifyState(VerifyDo verifyDo) {
        originalArticleMapper.modifyState(verifyDo);
    }
}
