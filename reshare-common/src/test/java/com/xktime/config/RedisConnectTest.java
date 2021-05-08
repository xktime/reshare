package com.xktime.config;


import com.xktime.config.redis.RedisUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareCommonApplication.class)
@RunWith(SpringRunner.class)
public class RedisConnectTest {

    @Autowired
    private RedisUtils redisUtils;

    @org.junit.Test
    public void testConnect() {
        String key = "1";
        boolean hasKey = redisUtils.exists(key);
        System.out.println("hasKey:" + hasKey);
        redisUtils.add(key, "key");
        hasKey = redisUtils.exists(key);
        System.out.println("hasKey:" + hasKey);
        System.out.println("Key:" + redisUtils.get(key));
    }
}
