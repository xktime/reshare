package com.xktime.model.pojo.user.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserArticleList {
    private int id;
    private int userId;
    private int channelId;
    private int articleId;
    private Boolean isShow;
    private Date recommendTime;
    private Boolean isRead;
    private int strategyId;

}