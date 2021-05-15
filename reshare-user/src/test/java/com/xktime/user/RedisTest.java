package com.xktime.user;

import com.xktime.model.pojo.user.entity.AppUser;
import com.xktime.model.templet.RestfulTemplet;
import com.xktime.user.service.impl.AppBaseUserServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareUserApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RestfulTemplet restfulTemplet;

    @org.junit.Test
    public void getValue() {
        AppUser user = restfulTemplet.getUserByAccount("z761628819");
    }

    @Autowired
    AppBaseUserServiceImpl userService;

    @org.junit.Test
    public void testAop() {
        userService.queryByAccount("z761628819");
    }
}
