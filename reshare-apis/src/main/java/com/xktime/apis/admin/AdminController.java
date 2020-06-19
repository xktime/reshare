package com.xktime.apis.admin;

import com.xktime.model.article.dtos.AuditDto;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.pojos.CrawlerArticle;
import com.xktime.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://ARTICLE";

    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadArticleDto dto) {
        return restTemplate.exchange(
                REST_URL_PREFIX + "/admin/loadArticle",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<ResponseResult<List<CrawlerArticle>>>() {
                }).getBody();
//        return restTemplate.postForObject(REST_URL_PREFIX + "/admin/loadArticle", dto, ResponseResult.class);
    }

    @GetMapping("audit")
    public void audit(AuditDto dto) {
        restTemplate.put(REST_URL_PREFIX + "/admin/audit", dto);
    }
}
