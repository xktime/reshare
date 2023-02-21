package com.xktime.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xktime.common", "com.xktime.comment","com.xktime.model"})
public class ReshareCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReshareCommentApplication.class, args);
    }

}
