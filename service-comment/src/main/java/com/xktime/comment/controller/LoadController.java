package com.xktime.comment.controller;

import com.xktime.comment.service.CommentService;
import com.xktime.comment.templet.RestfulTemplet;
import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.comment.entity.Comment;
import com.xktime.model.pojo.user.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("load")
public class LoadController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CommentService commentService;

    @Autowired
    RestfulTemplet restfulTemplet;

    @PostMapping("comment")
    public List<CommentDto> loadComment(@RequestBody LoadDto dto) {
        List<Comment> comments = commentService.load(dto.toQuery());
        List<CommentDto> commentDtos = new ArrayList<>();
        try {
            for (Comment comment : comments) {
                AppUser user = restfulTemplet.getUserByUserId(comment.getAuthorId());
                commentDtos.add(comment.toCommentDto(user.toSimpleUserDto()));
            }
        } catch (Exception e) {
            logger.error("获取评论失败", e);
        }
        return commentDtos;
    }

}
