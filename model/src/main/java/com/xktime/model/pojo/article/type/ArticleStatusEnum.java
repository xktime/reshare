package com.xktime.model.pojo.article.type;

import java.io.Serializable;

public enum ArticleStatusEnum implements Serializable {

    WAIT_AUDIT(0, "待审核文章"),
    UNPASSED(1, "未通过审核"),
    PASSED(2, "通过审核"),
    ;

    int status;
    String message;

    ArticleStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
