package com.xktime.apis.admin;

import com.xktime.model.pojo.account.dto.LoginDto;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.common.type.HttpCodeEnum;
import com.xktime.model.templet.RestfulTemplet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Api(tags = "后台管理系统相关接口")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestfulTemplet restfulTemplet;

    @ApiOperation("加载文章")
    @GetMapping("loadArticle")
    public ResponseResult loadArticle(LoadDto dto) {
        ResponseResult<List<VerifyArticleDto>> responseResult = new ResponseResult<>();
        try {
            responseResult.ok(restfulTemplet.loadArticle(restTemplate, dto));
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }

    @ApiOperation("审核文章")
    @GetMapping("verify")
    public ResponseResult verify(VerifyDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restfulTemplet.verifyArticle(restTemplate, dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(LoginDto dto) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult = restfulTemplet.loginAdmin(restTemplate, dto);
        } catch (Exception e) {
            responseResult.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return responseResult;
        }
        return responseResult;
    }
}
