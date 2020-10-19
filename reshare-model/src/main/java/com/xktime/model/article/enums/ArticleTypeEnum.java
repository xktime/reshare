package com.xktime.model.article.enums;

public enum ArticleTypeEnum {
    CRAWLER_ARTICLE("crawler"),
    ORIGINAL_ARTICLE("original"),
    COMMEND_ARTICLE("commend"),
    ;

    private String dec;

    ArticleTypeEnum(String dec) {
        this.dec = dec;
    }

    public String getDec() {
        return dec;
    }

}
