package com.xktime.model.util;

import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.dtos.c2s.VerifyDto;
import com.xktime.model.article.dtos.s2c.SimpleArticleDto;
import com.xktime.model.article.dtos.s2c.VerifyArticleDto;
import com.xktime.model.article.pos.Article;
import com.xktime.model.article.pos.CrawlerArticle;
import com.xktime.model.article.pos.OriginalArticle;
import org.springframework.beans.BeanUtils;

public class TransferUtils {

    public static com.xktime.model.article.dos.LoadDo toDO(com.xktime.model.article.dtos.c2s.LoadDto dto) {
        com.xktime.model.article.dos.LoadDo loadDo = new com.xktime.model.article.dos.LoadDo();
        BeanUtils.copyProperties(dto, loadDo);
        return loadDo;
    }

    public static com.xktime.model.comment.dos.LoadDo toDO(com.xktime.model.comment.dtos.c2s.LoadDto dto) {
        com.xktime.model.comment.dos.LoadDo loadDo = new com.xktime.model.comment.dos.LoadDo();
        BeanUtils.copyProperties(dto, loadDo);
        return loadDo;
    }

    public static VerifyDo toDO(VerifyDto dto) {
        VerifyDo verifyDo = new VerifyDo();
        BeanUtils.copyProperties(dto, verifyDo);
        return verifyDo;
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
