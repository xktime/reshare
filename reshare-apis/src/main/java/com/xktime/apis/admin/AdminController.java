package com.xktime.apis.admin;

import com.xktime.model.article.dtos.ArticleHomeDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://ARTICLE";

    @GetMapping("crawlerArticle")
    public ResponseResult loadCrawlerArticle(ArticleHomeDto dto) {
        ResponseResult responseResult = new ResponseResult();
        List<CrawlerArticle> crawlerArticleList = restTemplate.postForObject(REST_URL_PREFIX + "/admin/crawlerArticle", dto, ArrayList.class);
        responseResult.ok(crawlerArticleList);
        return responseResult;
    }

}
