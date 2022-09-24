package com.xktime.comment.controller;

import com.xktime.comment.service.CommentService;
import com.xktime.comment.templet.RestfulTemplate;
import com.xktime.model.pojo.comment.dto.c2s.PublishDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    RestfulTemplate restfulTemplet;

    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = restfulTemplet.getUserByToken(dto.getToken());
        if (author == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT);
        }
        commentService.saveComment(dto.toComment(author));
        return result;
    }
}
