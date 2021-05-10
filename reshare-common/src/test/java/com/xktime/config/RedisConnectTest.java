package com.xktime.config;


import com.xktime.utils.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareCommonApplication.class)
@RunWith(SpringRunner.class)
public class RedisConnectTest {

    @Autowired
    private RedisUtil redisUtil;

    @org.junit.Test
    public void testConnect() {
        String key = "3";
        boolean hasKey = redisUtil.exists(key);
        System.out.println("hasKey:" + hasKey);
        redisUtil.setAdd(key, "key33");
        hasKey = redisUtil.exists(key);
        System.out.println("hasKey:" + hasKey);
        System.out.println("Key:" + redisUtil.setMembers(key));
    }
}
