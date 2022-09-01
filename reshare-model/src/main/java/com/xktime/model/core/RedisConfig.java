package com.xktime.model.core;

import com.xktime.model.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import javax.annotation.Resource;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisUtil getRedisUtil() {
        return new RedisUtil(redisTemplate);
    }

//    @Bean
//    public static ConfigureRedisAction configureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
}