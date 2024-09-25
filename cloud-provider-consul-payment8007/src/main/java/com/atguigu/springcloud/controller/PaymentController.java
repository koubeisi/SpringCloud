package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zzyy
 * @since 2020-02-19 16:06
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 模拟服务调用
     * 1. 模拟异常；
     * 2. 模拟超时；
     * 3. 正常状态。
     *
     * @param id ID
     * @return String
     */
    @GetMapping("/pay/circuit/{id}")
    public Map<String, Object> circuit(@PathVariable Integer id) {
        if (id < 0) throw new IllegalArgumentException("id < 0");

        if (id > 100) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(e.getMessage(), e);
            }
        }
        return Map.of("port", serverPort, "msg", "Hello, circuit! Input Id: " + id + UUID.randomUUID());
    }
}
