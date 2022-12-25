package com.xktime.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.common","com.xktime.article","com.xktime.model", "com.xktime.utils"})
@EnableEurekaClient
public class ReshareArticleApplication {

    private static final Logger log= LoggerFactory.getLogger(ReshareArticleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReshareArticleApplication.class, args);
    }

}
