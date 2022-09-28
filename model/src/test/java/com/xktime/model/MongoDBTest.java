package com.xktime.model;

import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2022/8/9 16:35
 */
@SpringBootTest(classes = ReshareModelApplication.class)
@RunWith(SpringRunner.class)
public class MongoDBTest {
    @Autowired
    MongoTemplate mongoTemplate;


    @Test
    public void testConnect() {
        System.out.println("=================查询=======================");
        Query query = Query.query(Criteria.where("id").is(1));
        System.out.println(mongoTemplate.findOne(query, CrawlerVerifyArticle.class));
        CrawlerVerifyArticle article = new CrawlerVerifyArticle();
        article.setId(1);
        mongoTemplate.save(article);
        System.out.println("===================插入后查询============================");
        System.out.println(mongoTemplate.findOne(query, CrawlerVerifyArticle.class));
        Update update = Update.update("status", 3);
        mongoTemplate.findAndModify(query, update, CrawlerVerifyArticle.class);
        System.out.println("======================修改后查询=========================");
        System.out.println(mongoTemplate.findOne(query, CrawlerVerifyArticle.class));
    }

    @Autowired
    ICrawlerArticleDBService crawlerArticleDBService;
    @Test
    public void testUrlCount() {
        System.out.println(crawlerArticleDBService.getUrlCount("123"));
    }
}
