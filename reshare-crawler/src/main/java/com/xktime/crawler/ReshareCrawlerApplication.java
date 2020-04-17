package com.xktime.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.common", "com.xktime.crawler"})
public class ReshareCrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareCrawlerApplication.class, args);
    }
}
