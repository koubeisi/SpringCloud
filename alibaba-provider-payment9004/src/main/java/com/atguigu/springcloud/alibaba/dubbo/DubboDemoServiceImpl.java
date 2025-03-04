package com.atguigu.springcloud.alibaba.dubbo;

import com.atguigu.springcloud.api.DubboDemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author PC
 * @since 2025/3/4
 */
@DubboService
public class DubboDemoServiceImpl implements DubboDemoService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
