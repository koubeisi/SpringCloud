package com.atguigu.springcloud.api;

import com.atguigu.springcloud.api.fallback.PayClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author PC
 * @since 2024/9/24
 */
@FeignClient(name = "consul-provider-service",fallback = PayClientFallback.class)
public interface PayClient {

    @GetMapping("/pay/circuit/{id}")
    Map<String,Object> circuit(@PathVariable Integer id);
}
