package com.atguigu.springcloud.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author zzyy
 * @since 2020-02-20 9:40
 */
@Configuration
public class FeignConfig {
    /**
     * feign日志只是配置请求信息的完整度，具体的信息由日志级别（debug）决定
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;   // 全部日志
    }

    // 该功能也可在yml文件中配置
/*    @Bean
    public Request.Options feignOptions() {
        // 连接超时5000ms，读取超时10000ms
        return new Request.Options(Duration.ofSeconds(3), Duration.ofSeconds(5), false);
    }*/

    @Bean
    public Retryer feignRetryer() {
//        return Retryer.NEVER_RETRY;
        // 初始间隔100ms，最大间隔1秒，重试5次
        return new Retryer.Default(100, SECONDS.toMillis(1), 3);
    }

    // 自定义 ErrorDecoder，因为默认只重试超时异常，不重试 500 异常
//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new FeignErrorDecoder(); // 使用自定义的 ErrorDecoder
//    }
}
