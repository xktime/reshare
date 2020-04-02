package com.xktime.model.behavior.pojos;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
public class BehaviorEntry {
    private Integer id;
    private Short type;
    private Integer entryId;
    private Date createdTime;
    public String burst;
}