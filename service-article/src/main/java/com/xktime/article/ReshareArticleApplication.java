package com.xktime.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.common", "com.xktime.article", "com.xktime.model", "com.xktime.utils"})
public class ReshareArticleApplication {

    public static void main(String[] args) {
        try {

            SpringApplication.run(ReshareArticleApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
