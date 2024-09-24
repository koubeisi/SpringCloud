package com.atguigu.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author PC
 * @since 2024/9/24
 */
@FeignClient(name = "consul-provider-service")
public interface PayClient {

    @GetMapping("/pay/circuit/{id}")
    String circuit(@PathVariable Integer id);
}
