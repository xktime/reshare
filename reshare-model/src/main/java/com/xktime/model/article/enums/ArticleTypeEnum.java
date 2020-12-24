package com.xktime.model.article.enums;

public enum ArticleTypeEnum {
    CRAWLER_ARTICLE(1, "crawler"),
    ORIGINAL_ARTICLE(2, "original"),
    RECOMMEND_ARTICLE(3, "recommend"),
    ;

    private String dec;
    private int type;

    ArticleTypeEnum(int type, String dec) {
        this.type = type;
        this.dec = dec;
    }

    public static ArticleTypeEnum getEnum(int type)
    {
        ArticleTypeEnum[] values = ArticleTypeEnum.values();
        for (ArticleTypeEnum articleType : values) {
            if (articleType.getType() == type) {
                return articleType;
            }
        }
        return null;
    }

    public String getDec() {
        return dec;
    }

    public int getType() {
        return type;
    }

}
