package com.xktime.crawler;

import com.xktime.crawler.task.TaskMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ReshareCrawlerApplication.class)
public class CrawlerTest {

    @Autowired
    TaskMain taskMain;

    @Test
    public void test() {
        taskMain.crawling();
    }
}
