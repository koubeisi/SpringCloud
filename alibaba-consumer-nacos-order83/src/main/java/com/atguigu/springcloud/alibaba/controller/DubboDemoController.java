package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.api.DubboDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KouBeisi
 * @since 2025/3/4
 */
@RestController
public class DubboDemoController {

    private final DubboDemoService dubboDemoService;


    public DubboDemoController(DubboDemoService dubboDemoService) {
        this.dubboDemoService = dubboDemoService;
    }

    @GetMapping(value = "/dubbo/demo")
    public String dubboDemo(@RequestParam("name") String name) {
        return dubboDemoService.sayHello(name);
    }
}
