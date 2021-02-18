package com.xktime.comment.controller;

import com.xktime.comment.service.CommentService;
import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import com.xktime.model.pojo.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("load")
public class LoadController {

    @Autowired
    CommentService commentService;

    @RequestMapping("comment")
    public List<Comment> loadComment(@RequestBody LoadDto dto) {
        return commentService.load(dto.toQuery());
    }

}
