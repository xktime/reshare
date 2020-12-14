package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.service.impl.ArticleServiceImpl;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dtos.c2s.LoadDto;
import com.xktime.model.article.dtos.s2c.ArticleDetailsDto;
import com.xktime.model.article.dtos.s2c.SimpleArticleDto;
import com.xktime.model.article.dtos.s2c.VerifyArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import com.xktime.model.article.pos.Article;
import com.xktime.model.common.constant.GlobalConstant;
import com.xktime.model.util.TransferUtils;
import org.apache.commons.lang.StringUtils;
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

    @PostMapping("verifyArticle")
    public List<VerifyArticleDto> verifyArticle(@RequestBody LoadDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            throw new NullPointerException("LoadArticleType为空");
        }
        BaseArticleService<?> service = factory.getService(dto.getLoadArticleType());
        if (service == null) {
            throw new IllegalArgumentException("LoadArticleType参数错误：" + dto.getLoadArticleType());
        }
        if (dto.getSize() <= 0) {
            dto.setSize(GlobalConstant.DEFAULT_LOAD_ARTICLE_SIZE);
        }
        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }
        LoadDo loadDo = TransferUtils.toDO(dto);
        return service.loadVerifyArticleDtoListNotNull(loadDo);
    }

    @PostMapping("article")
    public List<SimpleArticleDto> article(@RequestBody LoadDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            throw new NullPointerException("LoadArticleType为空");
        }
        if (dto.getSize() <= 0) {
            dto.setSize(GlobalConstant.DEFAULT_LOAD_ARTICLE_SIZE);
        }
        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }
        LoadDo loadDo = TransferUtils.toDO(dto);
        if (!StringUtils.isEmpty(dto.getToken())
                && dto.getLoadArticleType().equals(ArticleTypeEnum.RECOMMEND_ARTICLE.getDec())) {
            //todo 根据玩家推荐文章
            return null;
        } else {
            return articleService.loadArticleDtoListNotNull(loadDo);
        }
    }

    @PostMapping("articleDetails")
    public ArticleDetailsDto articleDetails(long articleId) {
        ArticleDetailsDto detail = new ArticleDetailsDto();
        Article article = articleService.findById(articleId);
        BeanUtils.copyProperties(article, detail);
        //todo 插入detail评论
        //todo 评论分页
        return detail;
    }

}
