package com.xktime.crawler.pipe;


import com.xktime.crawler.task.TaskMain;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将爬取结果存储到数据库
 */
//todo com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'channel_name' at row 1
@Component
public class DatabasePipeline implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        if (TaskMain.cacheArticle.containsKey(url)) {
            return;
        }
        TaskMain.cacheArticle.put(url, trans(resultItems));
        //todo 存储需要优化
    }

    private CrawlerVerifyArticle trans(ResultItems resultItems) {
        CrawlerVerifyArticle article = new CrawlerVerifyArticle();
        String url = resultItems.getRequest().getUrl();
        Map<String, Object> all = resultItems.getAll();
        Object title = all.get("title");
        Object channelName = all.get("channelName");
        Object origin = all.get("origin");
        Object content = all.get("content");
        Object lables = all.get("lables");
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
