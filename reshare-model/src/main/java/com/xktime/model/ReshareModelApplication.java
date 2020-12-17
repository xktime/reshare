package com.xktime.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.config","com.xktime.model"})
public class ReshareModelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareModelApplication.class, args);
    }
}
