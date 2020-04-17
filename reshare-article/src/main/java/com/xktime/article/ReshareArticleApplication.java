package com.xktime.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xktime.common")
public class ReshareArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareArticleApplication.class, args);
    }

}
