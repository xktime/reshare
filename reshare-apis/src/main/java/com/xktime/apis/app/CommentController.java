package com.xktime.apis.app;

import com.xktime.model.pojo.comment.dto.c2s.PublishDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.templet.RestfulTemplet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "app评论相关接口")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    RestfulTemplet restfulTemplet;

    /**
     * 发布评论
     *
     * @param dto
     * @return
     */
    @ApiOperation("发布")
    @PostMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        try {
            return restfulTemplet.publishComment(dto);
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL);
            return result;
        }
    }
}
