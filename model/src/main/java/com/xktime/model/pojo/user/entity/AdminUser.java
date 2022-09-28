package com.xktime.model.pojo.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "AdminUser")
public class AdminUser extends BaseUser {
    private int sex;
    private String deptId;
    private int locked;
    private String remark;
    private int userType;
    private int enable;
}
