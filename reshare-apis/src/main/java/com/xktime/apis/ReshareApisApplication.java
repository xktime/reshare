package com.xktime.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.config","com.xktime.apis"})
@EnableEurekaClient
public class ReshareApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareApisApplication.class, args);
    }

}
