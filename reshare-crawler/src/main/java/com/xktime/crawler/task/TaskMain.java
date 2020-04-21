package com.xktime.crawler.task;

import com.xktime.crawler.pipe.DatabasePipeline;
import com.xktime.crawler.task.impl.CsdnCrawlerServiceImpl;
import com.xktime.crawler.task.impl.SegmentfaultCrawlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        csdnService.run(this.databasePipeline);
        segmentfaultService.run(this.databasePipeline);
        System.out.println("当前时间" + new Date());
    }

}
