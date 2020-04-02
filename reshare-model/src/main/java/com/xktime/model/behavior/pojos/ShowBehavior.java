package com.xktime.model.behavior.pojos;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class ShowBehavior {
    private Integer id;
    private Integer entryId;
    private Integer articleId;
    private Boolean isClick;
    private Date showTime;
    private Date createdTime;

}