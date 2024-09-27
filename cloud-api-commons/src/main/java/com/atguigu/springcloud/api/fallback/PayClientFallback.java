package com.atguigu.springcloud.api.fallback;

import com.atguigu.springcloud.api.PayClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * OpenFeign 的 fallback 机制似乎下下游服务错误时代替错误值返回,
 * 而此时再为此接口配置了@CircuitBreaker注解的调用方会认为这是个正确的调用
 * @author PC
 * @since 2024/9/27
 */
//@Component
public class PayClientFallback implements PayClient {
    @Override
    public Map<String, Object> circuit(Integer id) {
        return Map.of("error","Feign fallback");
    }
}
