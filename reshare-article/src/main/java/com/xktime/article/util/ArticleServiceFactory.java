package com.xktime.article.util;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.pojo.article.type.ArticleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceFactory {

    @Autowired
    @Qualifier("OriginalArticle")
    BaseArticleService originalBaseArticleService;

    @Autowired
    @Qualifier("CrawlerArticle")
    BaseArticleService crawlerBaseArticleService;

    public BaseArticleService getService(int articleType) {
        ArticleTypeEnum typeEnum = ArticleTypeEnum.getEnum(articleType);
        if (typeEnum == null) {
            return null;
        }
        switch (typeEnum) {
            case CRAWLER_ARTICLE:
                return crawlerBaseArticleService;
            case ORIGINAL_ARTICLE:
                return originalBaseArticleService;
            default:
                return null;
        }
    }
}
