package com.xktime.crawler.pipe;


import com.xktime.article.service.CrawlerArticleService;
import com.xktime.model.article.pojos.CrawlerArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.Map;

/**
 * 将爬取结果存储到数据库
 */
@Component
public class DatabasePipeline implements Pipeline {

    @Autowired
    CrawlerArticleService articleService;


    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        if (this.articleService.getUrlCount(url) == 0) {//防止重复爬取
            this.articleService.save(trans(resultItems));
        }

    }

    private CrawlerArticle trans(ResultItems resultItems) {
        CrawlerArticle article = new CrawlerArticle();
        String url = resultItems.getRequest().getUrl();
        Map<String, Object> all = resultItems.getAll();
        Object title = all.get("title");
        Object channelName = all.get("channelName");
        Object origin = all.get("origin");
        Object content = all.get("content");
        Object lables = all.get("lables");
        Object autherName = all.get("autherName");
        Object publishTime = all.get("publishTime");
        article.setUrl(url);
        if (title instanceof String) {
            article.setTitle((String) title);
        }
        if (autherName instanceof String) {
            article.setAuthorName((String) autherName);
        }
        if (content instanceof String) {
            article.setContent((String) content);
        }
        if (lables instanceof String) {
            article.setLabels((String) lables);
        }
        if (origin instanceof String) {
            article.setOrigin((String) origin);
        }
        if (channelName instanceof String) {
            article.setChannelName((String) channelName);
        }
        if (publishTime instanceof Date) {
            article.setPublishTime((Date) publishTime);
        }
        return article;
    }
}
