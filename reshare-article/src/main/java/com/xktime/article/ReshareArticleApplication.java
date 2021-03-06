package com.xktime.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.config","com.xktime.article","com.xktime.model"})
@EnableEurekaClient
public class ReshareArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareArticleApplication.class, args);
    }

}
