package com.xktime.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
//todo 分模块重构
public class ReshareCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareCommonApplication.class, args);
    }

}
