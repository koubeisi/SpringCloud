package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.api.DubboDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PC
 * @since 2025/3/4
 */
@RestController
public class DubboDemoConsumer implements CommandLineRunner {
    @DubboReference
    private DubboDemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        String result = demoService.sayHello("world");
        System.out.println("Receive result ======> " + result);
    }
}
