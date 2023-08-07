package com.xktime.crawler.pipe;


import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将爬取结果存储到数据库
 */
@Component
public class DatabasePipeline implements Pipeline {

    @Autowired
    ICrawlerArticleDBService articleDBService;

    public static Map<String, CrawlerVerifyArticle> cacheArticle = new ConcurrentHashMap<>();

    private final Object LOCK_ME = new Object();


    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        if (cacheArticle.containsKey(url)) {
            return;
        }
        if (articleDBService.getUrlCount(url) > 0){
            return;
        }
        synchronized (LOCK_ME) {
            cacheArticle.put(url, trans(resultItems));
        }
    }

    public void save() {
        if (!cacheArticle.isEmpty()) {
            synchronized (LOCK_ME) {
                if (!cacheArticle.isEmpty()) {
                    articleDBService.saveArticle(cacheArticle.values());
                    cacheArticle.clear();
                }
            }
        }
    }

    private CrawlerVerifyArticle trans(ResultItems resultItems) {
        CrawlerVerifyArticle article = new CrawlerVerifyArticle();
        String url = resultItems.getRequest().getUrl();
        Map<String, Object> all = resultItems.getAll();
        Object title = all.get("title");
        Object channelName = all.get("channelName");
        Object origin = all.get("origin");
        Object content = all.get("content");
        Object labels = all.get("labels");
        Object authorName = all.get("authorName");
        Object publishTime = all.get("publishTime");
        article.setUrl(url);
        if (title instanceof String) {
            article.setTitle((String) title);
        }
        if (authorName instanceof String) {
            article.setAuthorName((String) authorName);
        }
        if (content instanceof String) {
            article.setContent(Collections.singletonList((String) content));
        }
        if (labels instanceof String) {
            article.setLabels(Collections.singletonList((String) labels));
        }
        if (origin instanceof String) {
            article.setOrigin((String) origin);
        }
        if (channelName instanceof String) {
            article.setChannelName((String) channelName);
        }
        if (publishTime instanceof Long) {
            article.setPublishTime((long) publishTime);
        }
        return article;
    }
}
