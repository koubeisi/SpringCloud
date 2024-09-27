package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.api.PayClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author zzyy
 * @since 2020-02-19 16:24
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderConsulController {

    private final PayClient payClient;


    /**
     * consul 配置文件，用于测试动态刷新
     */
    @GetMapping("/consul/config")
    public String consulConfig(@Value("${consul.info}") String info) {
        return info;
    }

    /**
     * provider 根据不同的 ID 提供不同的响应状态，可以用来测试
     * 1. 超时控制
     * 2. 重试机制（结合日志打印）
     */
    @GetMapping("/feign/{id}")
    public Map<String, Object> feign(@PathVariable Integer id) {
        var stopWatch = new StopWatch();
        stopWatch.start();
        var result = payClient.circuit(id);
        stopWatch.stop();
        return Map.of("result", result, "Time", stopWatch.getTotalTimeMillis());
    }

    /**
     * provider 根据不同的 ID 提供不同的响应状态
     */
    @GetMapping("/circuit/{id}")
    @CircuitBreaker(name = "consul-provider-service", fallbackMethod = "circuitFallback")
    public Map<String, Object> circuit(@PathVariable Integer id) {
        return payClient.circuit(id);
    }

    public Map<String, Object> circuitFallback(Integer id, Throwable e) {
        log.error(e.getMessage(), e);
        return Map.of("error:" + id, "Fallback occurred at: " + System.currentTimeMillis());
    }


    /**
     * provider 根据不同的 ID 提供不同的响应状态
     */
    @GetMapping("/bulkhead/semaphore/{id}")
    @Bulkhead(name = "consul-provider-service", fallbackMethod = "bulkHeadFallback", type = Bulkhead.Type.SEMAPHORE)
    public Map<String, Object> bulkheadSemaphore(@PathVariable Integer id) {
        Map<String, Object> result;
        try {
            result = payClient.circuit(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Map.of("error", e.getMessage());
        }
        return result;
    }

    public String bulkHeadFallback(Throwable e) {
        log.error(e.getMessage(), e);
        return "fallback 服务繁忙请稍后再试:" + System.currentTimeMillis();
    }

    /**
     * provider 根据不同的 ID 提供不同的响应状态
     */
    @GetMapping("/bulkhead/threadPool/{id}")
    @Bulkhead(name = "consul-provider-service", fallbackMethod = "bulkHeadThreadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Map<String, Object>> bulkheadThreadPool(@PathVariable Integer id) {
        return CompletableFuture.supplyAsync(() -> payClient.circuit(id));
    }

    public String bulkHeadThreadPoolFallback(Throwable e) {
        log.error(e.getMessage(), e);
        return "fallback 服务繁忙请稍后再试:" + System.currentTimeMillis();
    }

}
