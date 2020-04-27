package com.xktime.apis.article;

import com.xktime.article.service.ArticleService;
import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.common.dtos.ResponseResult;
import com.xktime.model.common.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class ArticleHomeController{

//    @Autowired
//    ArticleService articleService;
//
//    @GetMapping("load")
//    public ResponseResult load(ArticleHomeDto dto) {
//        return articleService.load(dto, UserStatusEnum.LOGGED);
//    }

}
