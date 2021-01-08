package com.xktime.comment.controller;

import com.xktime.comment.service.CommentService;
import com.xktime.model.pojo.account.dto.PublishDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        //todo 发布评论
        return result;
    }
}
