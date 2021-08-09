package com.star;

import com.star.sys.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ErpApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void test() throws Exception {
        System.out.println(userService.findUserByUserName("system"));
    }

}
