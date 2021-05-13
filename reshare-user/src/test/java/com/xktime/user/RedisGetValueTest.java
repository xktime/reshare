package com.xktime.user;

import com.xktime.model.templet.RestfulTemplet;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareUserApplication.class)
@RunWith(SpringRunner.class)
public class RedisGetValueTest {

    @Autowired
    private RestfulTemplet restfulTemplet;

    @org.junit.Test
    public void getValue() {
        restfulTemplet.getUserByAccount("z761628819");
    }
}
