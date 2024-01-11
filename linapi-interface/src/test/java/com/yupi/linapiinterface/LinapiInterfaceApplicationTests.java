package com.yupi.linapiinterface;

import com.lin.linapiclientsdk.client.LinApiClient;
import com.lin.linapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LinapiInterfaceApplicationTests {

    @Resource
    private LinApiClient linApiClient;

    @Test
    void contextLoads() {
        String result = linApiClient.getNameByGet("lin");
        User user = new User();
        user.setUsername("linyk");
        String nameByPost = linApiClient.getNameByPost(user);
        System.out.println(result);
        System.out.println(nameByPost);
    }

}
