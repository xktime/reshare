package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.pojo.article.dto.c2s.LoadDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.type.ArticleTypeEnum;
import com.xktime.model.util.TransferUtils;
import com.xktime.utils.FormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    ArticleServiceFactory factory;

    @Autowired
    ArticleServiceImpl articleService;

    @PostMapping("verifyArticles")
    public List<VerifyArticleDto> verifyArticles(@RequestBody LoadDto dto) {
        BaseArticleService<?> service = factory.getService(dto.getLoadArticleType());
        if (service == null) {
            throw new IllegalArgumentException("LoadArticleType参数错误：" + dto.getLoadArticleType());
        }
        LoadQuery loadQuery = TransferUtils.toQuery(dto);
        return service.loadVerifyArticles(loadQuery);
    }

    @PostMapping("simpleArticles")
    public List<SimpleArticleDto> simpleArticles(@RequestBody LoadDto dto) {
        LoadQuery loadQuery = TransferUtils.toQuery(dto);
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
        ArticleDetailsDto detail = new ArticleDetailsDto();
        Article article = articleService.findById(id);
        BeanUtils.copyProperties(article, detail);
        //todo 插入detail评论
        //todo 评论分页
        return detail;
    }

}
