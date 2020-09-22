package com.xktime.article.service.impl;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.dtos.VerifyDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.article.pojos.OriginalArticle;
import com.xktime.model.mappers.article.CrawlerArticleMapper;
import com.xktime.model.mappers.article.OriginalArticleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    CrawlerArticleMapper crawlerArticleMapper;

    @Autowired
    OriginalArticleMapper originalArticleMapper;


    @Override
    public void saveCrawlerArticleList(List<CrawlerArticle> articleList) {
        if (articleList == null || articleList.isEmpty()) {
            return;
        }
        for (CrawlerArticle article : articleList) {
            saveCrawlerArticle(article);
        }
    }

    @Override
    public void saveCrawlerArticle(CrawlerArticle article) {
        crawlerArticleMapper.saveArticle(article);
    }

    @Override
    public void saveOriginalArticle(OriginalArticle article) {
        originalArticleMapper.saveArticle(article);
    }

    @Override
    public int getCrawlerArticleUrlCount(String url) {
        return crawlerArticleMapper.getUrlCount(url);
    }

    @Override
    public List<CrawlerArticle> loadCrawlerArticleList(LoadArticleDto dto) {
        return crawlerArticleMapper.load(dto);
    }

    @Override
    public List<VerifyArticleDto> loadVerifyArticleDtoList(LoadArticleDto dto) {
        List<VerifyArticleDto> verifyArticleList = new ArrayList<>();
        if (dto == null || dto.getSize() <= 0 || StringUtils.isEmpty(dto.getLoadArticleType())) {
            return verifyArticleList;
        }
        List articles = null;
        if (ArticleTypeEnum.CRAWLER_ARTICLE.getDec().equals(dto.getLoadArticleType())) {
            articles = loadCrawlerArticleList(dto);
        }
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

    @Override
    public void verify(VerifyDto dto) {
        crawlerArticleMapper.verify(dto);
    }
}
