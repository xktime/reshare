package com.xktime.crawler;

import com.xktime.article.service.CrawlerArticleService;
import com.xktime.crawler.pipe.DatabasePipeline;
import com.xktime.crawler.task.SegmentfaultTask;
import com.xktime.crawler.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.Date;

@Component
@EnableScheduling
public class TaskMain {

    @Autowired
    HttpUtils httpUtils;

    @Autowired
    CrawlerArticleService articleService;

    @Autowired
    private  DatabasePipeline databasePipeline;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void crawling() {
        Spider.create(new SegmentfaultTask())
                .addUrl("https://segmentfault.com/hottest")
                .addPipeline(this.databasePipeline)
                .run();
        System.out.println("当前时间" + new Date());
    }

}
