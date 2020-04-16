package com.xktime.crawler;

import com.xktime.crawler.util.HttpUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareCrawlerApplication.class)
@RunWith(SpringRunner.class)
public class HtmlTest {

    @Autowired
    HttpUtils httpUtils;

    @org.junit.Test
    public void test() {
        System.out.println(httpUtils.doGetHtml("https://bbs.nga.cn/"));
    }
}
