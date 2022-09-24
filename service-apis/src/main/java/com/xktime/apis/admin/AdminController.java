package com.xktime.apis.admin;

import com.xktime.apis.templet.RestfulTemplate;
import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "后台管理系统相关接口")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RestfulTemplate restfulTemplet;

    @ApiOperation("加载文章")
    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadDto dto) {
        ResponseResult<List<VerifyArticleDto>> responseResult = new ResponseResult<>();
        try {
            responseResult.ok(restfulTemplet.loadArticle(dto));
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL);
            return responseResult;
        }
        return responseResult;
    }

    @ApiOperation("审核文章")
    @GetMapping("verify")
    public ResponseResult verify(VerifyDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restfulTemplet.verifyArticle(dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL);
            return responseResult;
        }
        return responseResult;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult = restfulTemplet.loginAdmin(dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL);
            return responseResult;
        }
        return responseResult;
    }
}
