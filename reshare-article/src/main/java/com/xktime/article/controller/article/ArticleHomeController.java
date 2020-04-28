package com.xktime.article.controller.article;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class ArticleHomeController {

    @Autowired
    ArticleService articleService;

    @PostMapping("load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
        return articleService.load(dto, UserStatusEnum.LOGGED);
    }

}
