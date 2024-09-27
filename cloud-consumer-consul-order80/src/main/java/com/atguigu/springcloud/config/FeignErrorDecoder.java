package com.atguigu.springcloud.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

/**
 * @author PC
 * @since 2024/9/27
 */
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 500) {
            // 对于 5xx 错误，抛出 RetryableException 以触发重试机制
            return new RetryableException(
                    response.status(),
                    "Server error: " + response.status(),
                    response.request().httpMethod(),
                    0L, // Retry after, if you want to set retry delay
                    response.request());
        }
        // 处理非 5xx 的情况，不重试
        return new Exception("Non-retryable error: " + response.status());
    }
}
