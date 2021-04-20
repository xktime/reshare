package com.xktime.apis.app;

import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.c2s.PublishDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
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

import java.util.List;

@Api(tags = "app文章相关接口")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    RestfulTemplet restfulTemplet;

    /**
     * 发布文章
     *
     * @param dto
     * @return
     */
    @ApiOperation("发布")
    @PostMapping("publish")
    public ResponseResult publish(PublishDto dto) {
        ResponseResult result = new ResponseResult();
        try {
            return restfulTemplet.publishArticle(dto);
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
    }

    @ApiOperation("加载文章")
    @PostMapping("loadSimple")
    public ResponseResult loadSimpleArticle(LoadDto dto) {
        ResponseResult<List<SimpleArticleDto>> result = new ResponseResult<>();
        try {
            result.ok(restfulTemplet.loadSimpleArticle(dto));
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
        return result;
    }

    @ApiOperation("加载文章详情")
    @GetMapping("loadDetails")
    public ResponseResult loadArticleDetails(String articleId) {
        ResponseResult<ArticleDetailsDto> result = new ResponseResult<>();
        try {
            result.ok(restfulTemplet.loadArticleDetails(articleId));
        } catch (Exception e) {
            result.error(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getErrorMessage());
            return result;
        }
        return result;
    }
}
