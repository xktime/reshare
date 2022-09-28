package com.xktime.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = {"com.xktime.utils","com.xktime.model"})
@EnableRedisHttpSession
public class ReshareModelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareModelApplication.class, args);
    }
}
