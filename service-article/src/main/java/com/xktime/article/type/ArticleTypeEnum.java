package com.xktime.article.type;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.common.annotation.EnumTypeService;
import com.xktime.model.pojo.article.entity.BaseArticle;
import org.springframework.context.ApplicationContext;

public enum ArticleTypeEnum implements EnumTypeService {
    CRAWLER_ARTICLE(1, "crawler", CrawlerArticleServiceImpl.class),
    ORIGINAL_ARTICLE(2, "original", OriginalArticleServiceImpl.class),
    RECOMMEND_ARTICLE(3, "recommend", VerifiedArticleServiceImpl.class),
    ;

    private final String dec;
    private final int type;
    private final Class<? extends BaseArticleService> serviceClass;
    private BaseArticleService service;

    ArticleTypeEnum(int type, String dec, Class<? extends BaseArticleService> serviceClass) {
        this.type = type;
        this.dec = dec;
        this.serviceClass = serviceClass;
    }

    public static ArticleTypeEnum getEnum(int type) {
        for (ArticleTypeEnum articleType : ArticleTypeEnum.values()) {
            if (articleType.getType() == type) {
                return articleType;
            }
        }
        return null;
    }

    public static BaseArticleService getService(int type) {
        ArticleTypeEnum typeEnum = getEnum(type);
        return typeEnum == null ? null : typeEnum.getService();
    }

    public int getType() {
        return type;
    }

    public String getDec() {
        return dec;
    }

    public BaseArticleService getService() {
        return service;
    }

    public void setService(ApplicationContext bean) {
        this.service = bean.getBean(serviceClass);
    }
}
