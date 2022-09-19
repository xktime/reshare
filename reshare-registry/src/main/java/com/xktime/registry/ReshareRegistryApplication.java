package com.xktime.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ReshareRegistryApplication {
    public static void main(String[] args) {
        try {

            SpringApplication.run(ReshareRegistryApplication.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
