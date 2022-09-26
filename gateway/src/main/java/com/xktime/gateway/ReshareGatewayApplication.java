package com.xktime.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2022/9/26 9:56
 */
@SpringBootApplication
@EnableEurekaClient
public class ReshareGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareGatewayApplication.class, args);
    }
}
