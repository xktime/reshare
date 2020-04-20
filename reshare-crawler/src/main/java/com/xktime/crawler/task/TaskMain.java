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
    private DatabasePipeline databasePipeline;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void crawling() {
        new CsdnTaskImpl().run(this.databasePipeline);
        new SegmentfaultTaskImpl().run(this.databasePipeline);
        System.out.println("当前时间" + new Date());
    }

}
