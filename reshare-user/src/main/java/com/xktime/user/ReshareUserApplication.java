package com.xktime.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.config","com.xktime.user"})
@EnableEurekaClient
public class ReshareUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareUserApplication.class, args);
    }

}
