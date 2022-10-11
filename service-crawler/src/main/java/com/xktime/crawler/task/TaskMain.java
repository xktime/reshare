package com.xktime.crawler.task;

import com.xktime.crawler.pipe.DatabasePipeline;
import com.xktime.crawler.task.impl.CsdnCrawlerServiceImpl;
import com.xktime.crawler.task.impl.SegmentfaultCrawlerServiceImpl;
import com.xktime.model.pojo.article.entity.CrawlerVerifyArticle;
import com.xktime.model.services.ICrawlerArticleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskMain {

    @Autowired
    DatabasePipeline databasePipeline;

    @Autowired
    CsdnCrawlerServiceImpl csdnService;

    @Autowired
    SegmentfaultCrawlerServiceImpl segmentfaultService;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void crawling() {
        try {
            csdnService.run(this.databasePipeline);
            segmentfaultService.run(this.databasePipeline);
            this.databasePipeline.save();
            System.out.println("当前时间" + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
