package com.koubs.cloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KouBeisi
 * @since 2021/6/18
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Oauth2.0";
    }
}
