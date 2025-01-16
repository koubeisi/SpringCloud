package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author zzyy
 * @since  2020-02-25 15:32
 */
public class CustomerBlockHandler {

    private CustomerBlockHandler(){}

    public static CommonResult<Void> handlerException(BlockException exception) {
        return new CommonResult<>(4444, "按客戶自定义,global handlerException----1");
    }

    public static CommonResult<Void> handlerException2(BlockException exception) {
        return new CommonResult<>(4444, "按客戶自定义,global handlerException----2");
    }
}
