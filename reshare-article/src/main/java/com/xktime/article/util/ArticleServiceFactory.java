package com.xktime.article.util;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.article.enums.ArticleTypeEnum;
import org.apache.commons.lang.StringUtils;
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

    public BaseArticleService getService(String articleType) {
        if (StringUtils.isEmpty(articleType)) {
            return null;
        }
        if (articleType.equals(ArticleTypeEnum.CRAWLER_ARTICLE.getDec())) {
            return crawlerBaseArticleService;
        } else if (articleType.equals(ArticleTypeEnum.ORIGINAL_ARTICLE.getDec())) {
            return originalBaseArticleService;
        }
        return null;
    }
}
