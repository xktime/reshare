package com.xktime.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2022/9/24 14:59
 */
@SpringBootApplication
@EnableConfigServer
//todo localhost配置要整理
//todo docker数据库网络别名
public class ReshareConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareConfigApplication.class, args);
    }
}
