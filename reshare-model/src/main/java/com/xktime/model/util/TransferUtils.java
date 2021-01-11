package com.xktime.model.util;

import com.xktime.model.pojo.account.dto.RegisterDto;
import com.xktime.model.pojo.article.dto.c2s.VerifyDto;
import com.xktime.model.pojo.article.dto.s2c.ArticleDetailsDto;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.dto.s2c.VerifyArticleDto;
import com.xktime.model.pojo.article.entity.Article;
import com.xktime.model.pojo.article.entity.BaseVerifyArticle;
import com.xktime.model.pojo.article.entity.CrawlerArticle;
import com.xktime.model.pojo.article.entity.OriginalArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.pojo.article.query.VerifyQuery;
import com.xktime.model.pojo.comment.dto.c2s.LoadDto;
import com.xktime.model.pojo.common.constant.CodeConstant;
import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.utils.CodeUtil;
import com.xktime.utils.SnowflakeIdUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

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

    public static Article toArticle(BaseVerifyArticle baseArticle) {
        Article article = new Article();
        BeanUtils.copyProperties(baseArticle, article);
        return article;
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

    public static ArticleDetailsDto toArticleDetailsDto(Article article) {
        //todo 插入detail评论整合到TransferUtils
        ArticleDetailsDto dto = new ArticleDetailsDto();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static AppUser toUser(RegisterDto dto, SnowflakeIdUtil snowflakeId) {
        AppUser user = new AppUser();
        BeanUtils.copyProperties(dto, user);
        String password = CodeUtil.encryptBase64(dto.getPassword(), CodeConstant.LOGIN_PASSWORD_BASE64_KEY);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUserName(dto.getAccount());
        user.setUserId(snowflakeId.nextId());
        user.setProfile("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        return user;
    }
}
