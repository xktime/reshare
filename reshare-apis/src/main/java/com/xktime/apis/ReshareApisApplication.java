package com.xktime.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.common","com.xktime.article","com.xktime.apis"})
public class ReshareApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareApisApplication.class, args);
    }

}
