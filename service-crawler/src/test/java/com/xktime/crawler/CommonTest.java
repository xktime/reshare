package com.xktime.crawler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(classes = ReshareCrawlerApplication.class)
public class CommonTest {

    @Test
    public void test() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "2020-01-27 12:00:00";
        try {
            Date date = format2.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
