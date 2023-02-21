package com.xktime.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.utils", "com.xktime.model"})
//@EnableRedisHttpSession
public class ReshareModelApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ReshareModelApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
