package com.xktime.model;


import com.xktime.model.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.xktime.model.redis.RedisCommonKey;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2022/8/9 16:08
 */
@SpringBootTest(classes = ReshareModelApplication.class)
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testConnect() {
        redisUtil.set(RedisCommonKey.APP_USR, 1, "heihei");
    }

}
