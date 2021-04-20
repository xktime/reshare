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
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.comment.type.CommentTypeEnum;
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
        if (service == null) {
            throw new IllegalArgumentException("LoadArticleType参数错误：" + dto.getLoadArticleType());
        }
        if (!(service instanceof BaseAuditable)) {
            throw new IllegalArgumentException("articleType错误:" + dto.getLoadArticleType());
        }
        LoadQuery loadQuery = dto.toQuery();
        return ((BaseAuditable) service).loadVerifyArticles(loadQuery);
    }

    @PostMapping("simpleArticles")
    public List<SimpleArticleDto> simpleArticles(@RequestBody LoadDto dto) {
        LoadQuery loadQuery = dto.toQuery();
        if (!StringUtils.isBlank(dto.getToken())
                && dto.getLoadArticleType() == ArticleTypeEnum.RECOMMEND_ARTICLE.getType()) {
            //todo 根据玩家推荐文章
            return null;
        } else {
            return articleService.loadSimpleArticles(loadQuery);
        }
    }

    @PostMapping("articleDetails")
    public ArticleDetailsDto articleDetails(@RequestBody String articleId) {
        if (!FormatUtil.isNumber(articleId)) {
            return null;
        }
        long id = Long.parseLong(articleId);
        Article article = articleService.findById(id);
        //todo Transfer 加载评论重构
        com.xktime.model.pojo.comment.dto.c2s.LoadDto loadDto = new com.xktime.model.pojo.comment.dto.c2s.LoadDto();
        loadDto.setBindId(id);
        loadDto.setLoadCommentType(CommentTypeEnum.ARTICLE.getType());
        //todo 插入detail评论
        //todo 评论分页
        return article.toArticleDetailsDto(restfulTemplet.getComments(loadDto));
    }

}
