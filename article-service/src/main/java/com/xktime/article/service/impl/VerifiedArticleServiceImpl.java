package com.xktime.article.service.impl;

import com.xktime.article.service.BaseArticleService;
import com.xktime.model.mappers.article.VerifiedArticleMapper;
import com.xktime.model.pojo.article.dto.s2c.SimpleArticleDto;
import com.xktime.model.pojo.article.entity.VerifiedArticle;
import com.xktime.model.pojo.article.query.LoadQuery;
import com.xktime.model.redis.RedisCommonKey;
import com.xktime.model.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 已被审核通过的文章
 */
@Service("Article")
public class VerifiedArticleServiceImpl implements BaseArticleService<VerifiedArticle> {

    @Autowired
    VerifiedArticleMapper verifiedArticleMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(VerifiedArticle verifiedArticle) {
        verifiedArticleMapper.saveArticle(verifiedArticle);
    }

    @Override
    public void saveArticles(Collection<VerifiedArticle> verifiedArticles) {
        if (verifiedArticles == null || verifiedArticles.isEmpty()) {
            return;
        }
        for (VerifiedArticle verifiedArticle : verifiedArticles) {
            save(verifiedArticle);
        }
    }

    @Override
    public void update(VerifiedArticle verifiedArticle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public VerifiedArticle findById(long id) {
        VerifiedArticle verifiedArticle = redisUtil.mapGet(RedisCommonKey.COMMON_ARTICLE, id);
        return verifiedArticle != null ? verifiedArticle : verifiedArticleMapper.findById(id);
    }

    @Override
    public List<VerifiedArticle> loadArticles(LoadQuery loadQuery) {
        return verifiedArticleMapper.load(loadQuery);
    }

    @Override
    public List<SimpleArticleDto> loadSimpleArticles(LoadQuery loadQuery) {
        List<SimpleArticleDto> SimpleArticles = new ArrayList<>();
        List<VerifiedArticle> verifiedArticles = loadArticles(loadQuery);
        if (verifiedArticles != null && !verifiedArticles.isEmpty()) {
            for (VerifiedArticle verifiedArticle : verifiedArticles) {
                SimpleArticleDto simpleArticle = verifiedArticle.toSimpleArticleDto();
                SimpleArticles.add(simpleArticle);
            }
        }
        return SimpleArticles;
    }

    public void removeById(@Param("id") long id) {
        verifiedArticleMapper.removeById(id);
    }

}
