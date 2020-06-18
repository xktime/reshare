package com.xktime.apis.article;

import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("home")
public class ArticleHomeController {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://ARTICLE";

    @GetMapping("load")
    public ResponseResult load(LoadArticleDto dto) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/home/load", dto, ResponseResult.class);
    }

}
