package com.xktime.crawler;

import com.xktime.crawler.task.TaskMain;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareCrawlerApplication.class)
@RunWith(SpringRunner.class)
public class CrawlerTest {

    @Autowired
    TaskMain taskMain;

    @org.junit.Test
    public void test() {
        taskMain.crawling();
    }
}
