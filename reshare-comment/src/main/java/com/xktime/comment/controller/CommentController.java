package com.xktime.comment.controller;

import com.xktime.comment.service.CommentService;
import com.xktime.model.pojo.comment.dto.c2s.PublishDto;
import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.templet.RestfulTemplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestfulTemplet restfulTemplet;

    @RequestMapping("publish")
    public ResponseResult publish(@RequestBody PublishDto dto) {
        ResponseResult result = new ResponseResult();
        AppUser author = restfulTemplet.getUserByToken(restTemplate, dto.getToken());
        if (author == null) {
            return result.error(HttpCodeEnum.NOT_FIND_ACCOUNT.getCode(), HttpCodeEnum.NOT_FIND_ACCOUNT.getErrorMessage());
        }
        //todo 移动到TransferUtil
        Comment comment = new Comment();
        comment.setAuthorId(author.getId());
        comment.setBindId(dto.getBindId());
        comment.setType(dto.getType());
        comment.setContent(dto.getContent());
        commentService.saveComment(comment);
        return result;
    }
}
