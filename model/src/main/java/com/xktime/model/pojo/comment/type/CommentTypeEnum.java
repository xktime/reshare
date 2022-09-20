package com.xktime.model.pojo.comment.type;

public enum CommentTypeEnum {
    ARTICLE(1, "文章评论"),
    ;

    int type;
    String dec;

    CommentTypeEnum(int status, String dec){
        this.type = status;
        this.dec = dec;
    }

    public int getType() {
        return type;
    }

    public String getDec() {
        return dec;
    }
}
