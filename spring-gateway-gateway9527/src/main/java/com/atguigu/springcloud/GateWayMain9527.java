package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zzyy
 * @date 2020-02-21 11:02
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class GateWayMain9527
{
    public static void main(String[] args) {
            SpringApplication.run(GateWayMain9527.class, args);
    }
}
