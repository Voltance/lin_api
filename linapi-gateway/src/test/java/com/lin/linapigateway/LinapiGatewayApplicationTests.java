package com.lin.linapigateway;

import com.lin.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableDubbo
class LinapiGatewayApplicationTests {

    @DubboReference
    private RpcDemoService rpcDemoService;

//    @Test
//    void contextLoads() {
//    }

    @Test
    void testRpc() {
        System.out.println(rpcDemoService.sayHello("lin"));
    }

}
