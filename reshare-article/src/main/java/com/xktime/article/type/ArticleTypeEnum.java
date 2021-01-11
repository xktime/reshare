package com.xktime.article.type;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.service.impl.CrawlerArticleServiceImpl;
import com.xktime.article.service.impl.OriginalArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public enum ArticleTypeEnum {
    CRAWLER_ARTICLE(1, "crawler", CrawlerArticleServiceImpl.class),
    ORIGINAL_ARTICLE(2, "original", OriginalArticleServiceImpl.class),
    RECOMMEND_ARTICLE(3, "recommend", ArticleServiceImpl.class),
    ;

    private String dec;
    private int type;
    private Class<? extends BaseArticleService> serviceClass;
    private BaseArticleService service;

    /**
     * 注入service
     */
    //todo 需要给该类移动到通用模块
    @Component
    public static class EnumTypeServiceInjector {
        @Autowired
        private ApplicationContext bean;

        /**
         * 在@Autowired执行之后会走这个方法
         */
        @PostConstruct
        public void postConstruct() {
            for (ArticleTypeEnum type : ArticleTypeEnum.values()) {
                type.setBean(bean);
            }
        }
    }

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

    private void setBean(ApplicationContext bean) {
        this.setService(bean.getBean(serviceClass));
    }

    private void setService(BaseArticleService service) {
        this.service = service;
    }
}
