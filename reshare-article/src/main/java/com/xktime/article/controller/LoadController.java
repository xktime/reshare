package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.BaseAuditable;
import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.type.ArticleTypeEnum;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.comment.dto.s2c.CommentDto;
import com.xktime.model.pojo.user.dto.s2c.SimpleUserDto;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.templet.RestfulTemplet;
import com.xktime.utils.FormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("load")
public class LoadController {

    @Autowired
    ArticleServiceImpl articleService;

    @Autowired
    RestfulTemplet restfulTemplet;

    @PostMapping("verifyArticles")
    public List<VerifyArticleDto> verifyArticles(@RequestBody LoadDto dto) {
        BaseArticleService<?> service = ArticleTypeEnum.getService(dto.getLoadArticleType());
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getLoadArticleType());
        }
        return ((BaseAuditable) service).loadVerifyArticles(dto.toQuery());
    }

    @PostMapping("simpleArticles")
    public List<SimpleArticleDto> simpleArticles(@RequestBody LoadDto dto) {
        if (!StringUtils.isBlank(dto.getToken())
                && dto.getLoadArticleType() == ArticleTypeEnum.RECOMMEND_ARTICLE.getType()) {
            //todo 根据玩家推荐文章
            return null;
        } else {
            return articleService.loadSimpleArticles(dto.toQuery());
        }
    }

    @PostMapping("articleDetails")
    public ArticleDetailsDto articleDetails(@RequestBody String articleId) {
        if (!FormatUtil.isNumber(articleId)) {
            return null;
        }
        long id = Long.parseLong(articleId);
        Article article = articleService.findById(id);

        //获取评论详情
        List<CommentDto> comments = null;
        try{
            comments = restfulTemplet.getComments(article.getCommentLoadDto());
        } catch (Exception e) {
            //todo 打印报错日志
        }

        //获取文章作者信息
        SimpleUserDto author = null;
        try{
            AppUser user = restfulTemplet.getUserByUserId(article.getAuthorId());
            author = user.toSimpleUserDto();
        } catch (Exception e) {
            //todo 打印报错日志
        }
        return article.toArticleDetailsDto(comments, author);
    }

}
