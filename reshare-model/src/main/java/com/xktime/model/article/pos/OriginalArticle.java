package com.xktime.model.article.pos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * 平台原创文章
 */
@Data
public class OriginalArticle extends BaseVerifyArticle {
    private long id;
    private String title;
    private String content;
    private String authorName;
    private long authorId;
    private String channelName;
    private String labels;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String url;
    private String origin;
    private int status;

}
