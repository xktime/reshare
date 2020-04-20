package com.xktime.crawler.task;

import com.xktime.article.service.CrawlerArticleService;
import com.xktime.crawler.pipe.DatabasePipeline;
import com.xktime.crawler.task.impl.CsdnTaskImpl;
import com.xktime.crawler.task.impl.SegmentfaultTaskImpl;
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
    CrawlerArticleService articleService;

    @Autowired
    private DatabasePipeline databasePipeline;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void crawling() {
        Spider.create(new SegmentfaultTaskImpl())
                .addUrl("https://segmentfault.com/hottest")
                .addUrl("https://segmentfault.com/newest")
                .addUrl("https://segmentfault.com/")
                .addPipeline(this.databasePipeline)
                .run();
        Spider.create(new CsdnTaskImpl())
                .addUrl("https://www.csdn.net/")
                .addUrl("https://www.csdn.net/nav/java")
                .addUrl("https://www.csdn.net/nav/career")
                .addUrl("https://www.csdn.net/nav/fund")
                .addPipeline(this.databasePipeline)
                .run();
        System.out.println("当前时间" + new Date());
    }

}
