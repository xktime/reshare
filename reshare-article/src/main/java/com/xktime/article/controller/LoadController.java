package com.xktime.article.controller;

import com.xktime.article.service.BaseArticleService;
import com.xktime.article.util.ArticleServiceFactory;
import com.xktime.model.article.dtos.LoadArticleDto;
import com.xktime.model.article.dtos.VerifyArticleDto;
import com.xktime.model.article.enums.ArticleTypeEnum;
import org.apache.commons.lang.StringUtils;
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

    @PostMapping("article")
    public List<VerifyArticleDto> article(@RequestBody LoadArticleDto dto) {
        if (StringUtils.isEmpty(dto.getLoadArticleType())) {
            throw new NullPointerException("LoadArticleType为空");
        }
        BaseArticleService<?> service = factory.getService(dto.getLoadArticleType());
        if (service == null) {
            throw new IllegalArgumentException("LoadArticleType参数错误：" + dto.getLoadArticleType());
        }
        if (dto.getSize() <= 0) {
            dto.setSize(10);
        }
        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }
        if (StringUtils.isEmpty(dto.getToken())
                && dto.getLoadArticleType().equals(ArticleTypeEnum.COMMEND_ARTICLE.getDec())) {
            //todo 根据玩家推荐文章
        }
        return service.loadVerifyArticleDtoListNotNull(dto);
    }

}
