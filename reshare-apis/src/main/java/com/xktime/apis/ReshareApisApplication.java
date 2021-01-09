package com.xktime.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.xktime.config","com.xktime.apis","com.xktime.model"})
@EnableEurekaClient
@EnableSwagger2
public class ReshareApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareApisApplication.class, args);
    }

}
