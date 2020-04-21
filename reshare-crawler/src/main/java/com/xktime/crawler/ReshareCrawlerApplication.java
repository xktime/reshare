package com.xktime.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.xktime.common", "com.xktime.crawler", "com.xktime.article"})
@EnableScheduling
public class ReshareCrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareCrawlerApplication.class, args);
    }
}
