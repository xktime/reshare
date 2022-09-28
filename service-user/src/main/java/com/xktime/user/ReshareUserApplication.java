package com.xktime.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.utils","com.xktime.user","com.xktime.model"})
@EnableEurekaClient
public class ReshareUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareUserApplication.class, args);
    }

}
