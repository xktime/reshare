package com.xktime.model.account.dtos;

import com.xktime.model.user.pojos.AppUser;
import lombok.Data;

@Data
public class PublishDto {
    String title;
    String content;
    String token;
    AppUser user;
}
