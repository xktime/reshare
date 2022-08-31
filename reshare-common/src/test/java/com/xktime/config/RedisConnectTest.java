package com.xktime.config;


import com.xktime.redis.RedisCommonKey;
import com.xktime.redis.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest(classes = ReshareCommonApplication.class)
@RunWith(SpringRunner.class)
public class RedisConnectTest {

    @Autowired
    private RedisUtil redisUtil;

    @org.junit.Test
    public void testConnect() {
        RedisCommonKey key = RedisCommonKey.APP_USR;
        boolean hasKey = redisUtil.exists(key);
        System.out.println("hasKey:" + hasKey);
        redisUtil.sAdd(key, "key33");
        redisUtil.sAdd(key, "key44");
        hasKey = redisUtil.exists(key);
        System.out.println("hasKey:" + hasKey);
        Set<String> set = redisUtil.sMembers(key);
        System.out.println("Key:" + set);
    }
}
