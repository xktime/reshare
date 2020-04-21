package com.xktime.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.common","com.xktime.admin", "com.xktime.article"})
public class ReshareAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReshareAdminApplication.class, args);
    }
}
