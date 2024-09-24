package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.api.PayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzyy
 * @since 2020-02-19 16:24
 */
@RestController
@RequiredArgsConstructor
public class OrderConsulController
{
    private final PayClient payClient;

    /**
     * 测试断路器的三种中通
     * provider 根据不同的 ID 提供不同的响应状态
     */
    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable Integer id) {
        return payClient.circuit(id);
    }


    /**
     * consul 配置文件，用于测试动态刷新
     */
    @GetMapping("/consul/config")
    public String consulConfig(@Value("${consul.info}") String info) {
        return info;
    }
}
