package com.xktime.model.pojo.article.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 爬虫爬取的文章
 */
@Data
@Document(collection = "CrawlerVerifyArticle")
public class CrawlerVerifyArticle extends BaseVerifyArticle {

}
