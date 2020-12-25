package com.xktime.model.util;

import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.article.entity.CrawlerArticle;
import com.xktime.model.pojo.article.entity.OriginalArticle;
import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import org.springframework.beans.BeanUtils;

public class TransferUtils {

    public static LoadQuery toQuery(com.xktime.model.pojo.article.dto.c2s.LoadDto dto) {
        LoadQuery loadQuery = new LoadQuery();
        BeanUtils.copyProperties(dto, loadQuery);
        return loadQuery;
    }

    public static com.xktime.model.pojo.comment.query.LoadQuery toQuery(LoadDto dto) {
        com.xktime.model.pojo.comment.query.LoadQuery loadQuery = new com.xktime.model.pojo.comment.query.LoadQuery();
        BeanUtils.copyProperties(dto, loadQuery);
        return loadQuery;
    }

    public static VerifyQuery toQuery(VerifyDto dto) {
        VerifyQuery verifyQuery = new VerifyQuery();
        BeanUtils.copyProperties(dto, verifyQuery);
        return verifyQuery;
    }

    public static VerifyArticleDto toVerifyArticleDto(Article article) {
        VerifyArticleDto dto = new VerifyArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static VerifyArticleDto toVerifyArticleDto(CrawlerArticle article) {
        VerifyArticleDto dto = new VerifyArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static VerifyArticleDto toVerifyArticleDto(OriginalArticle article) {
        VerifyArticleDto dto = new VerifyArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static SimpleArticleDto toSimpleArticleDto(Article article) {
        SimpleArticleDto dto = new SimpleArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static SimpleArticleDto toSimpleArticleDto(CrawlerArticle article) {
        SimpleArticleDto dto = new SimpleArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static SimpleArticleDto toSimpleArticleDto(OriginalArticle article) {
        SimpleArticleDto dto = new SimpleArticleDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }
}
