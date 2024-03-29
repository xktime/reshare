package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.article.service.impl.VerifiedArticleServiceImpl;
import com.xktime.article.templet.RestfulTemplate;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.common.dto.ResponseResult;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.utils.util.FormatUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("load")
@Api(tags = "文章加载")
public class LoadController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    VerifiedArticleServiceImpl verifiedArticleService;

    @Autowired
    RestfulTemplate restfulTemplate;

    @PostMapping("verifyArticles")
    public ResponseResult<List<VerifyArticleDto>> verifyArticles(LoadDto dto) {
        var service = ArticleTypeEnum.getService(dto.getLoadArticleType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getLoadArticleType());
        }
        return ResponseResult.okResult(((BaseAuditable) service).loadVerifyArticles(dto.toQuery()));
    }


    @PostMapping("simpleArticles")
    public ResponseResult<List<SimpleArticleDto>> simpleArticles(LoadDto dto) {
        if (!StringUtils.isBlank(dto.getToken())
                && dto.getLoadArticleType() == ArticleTypeEnum.RECOMMEND_ARTICLE.getType()) {
            //todo 根据玩家推荐文章
            return ResponseResult.okResult(verifiedArticleService.loadSimpleArticles(dto.toQuery()));
        } else {
            return ResponseResult.okResult(verifiedArticleService.loadSimpleArticles(dto.toQuery()));
        }
    }

    @GetMapping("articleDetails")
    public ResponseResult<ArticleDetailsDto> articleDetails(String articleId) {
        if (!FormatUtil.isNumber(articleId)) {
            return null;
        }
        long id = Long.parseLong(articleId);
        var verifiedArticle = verifiedArticleService.findById(id);

        //获取评论详情
        List<CommentDto> comments = null;
        try {
            comments = restfulTemplate.getComments(verifiedArticle.getCommentLoadDto());
        } catch (Exception e) {
            logger.error("获取评论详情失败", e);
        }

        //获取文章作者信息
        SimpleUserDto author = null;
        try {
            var user = restfulTemplate.getUserByUserId(verifiedArticle.getAuthorId());
            author = user.toSimpleUserDto();
        } catch (Exception e) {
            logger.error("获取文章作者失败", e);
        }
        return ResponseResult.okResult(verifiedArticle.toArticleDetailsDto(comments, author));
    }

}
