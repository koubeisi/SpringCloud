package com.koubs.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author KouBeisi
 * @since 2021/6/17
 */
@EnableResourceServer
@SpringBootApplication
public class AuthResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthResourceApplication.class, args);
    }
}
