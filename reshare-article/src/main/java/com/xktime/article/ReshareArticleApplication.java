package com.xktime.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.common","com.xktime.article"})
@EnableEurekaClient
public class ReshareArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareArticleApplication.class, args);
    }

}
