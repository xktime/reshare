package com.xktime.model.article.enums;

public enum ArticleTypeEnum {
    CRAWLER_ARTICLE("crawler"),
    ;

    private String dec;

    ArticleTypeEnum(String dec) {
        this.dec = dec;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
