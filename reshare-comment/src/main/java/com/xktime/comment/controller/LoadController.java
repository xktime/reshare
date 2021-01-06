package com.xktime.comment.controller;

import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("load")
public class LoadController {


    @Autowired
    RestTemplate restTemplate;

    @Value("${restful.url.comment}")
    private String COMMENT_REST_URL_PREFIX;

    @RequestMapping("load")
    public ResponseResult load(@RequestBody LoadDto dto) {
        ResponseResult result = new ResponseResult();

        return result;
    }

}
