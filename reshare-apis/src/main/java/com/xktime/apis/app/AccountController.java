package com.xktime.apis.app;

import com.xktime.model.account.dtos.PublishDto;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    /**
     * 发布文章
     *
     * @param dto
     * @return
     */
    @PostMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        return result;
    }
}
