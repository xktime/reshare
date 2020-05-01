package com.xktime.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.xktime.common","com.xktime.gateway"})
@EnableEurekaClient
public class ReshareGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareGatewayApplication.class, args);
    }
}
