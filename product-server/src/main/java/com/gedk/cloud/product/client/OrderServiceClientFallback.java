package com.gedk.cloud.product.client;

import org.springframework.stereotype.Component;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/13 21:07
 */
@Component
public class OrderServiceClientFallback implements OrderServiceClient {
    @Override
    public String orders() {
        return "orders fallback";
    }
}
