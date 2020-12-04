package com.xktime.model.user.pos;

import lombok.Data;

import java.util.Date;

@Data
public class UserArticleList {
    private Integer id;
    private Integer userId;
    private Integer channelId;
    private Integer articleId;
    private Boolean isShow;
    private Date recommendTime;
    private Boolean isRead;
    private Integer strategyId;

}