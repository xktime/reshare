package com.xktime.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.config", "com.xktime.comment"})
@EnableEurekaClient
public class ReshareCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareCommentApplication.class, args);
    }

}
