package com.gedk.cloud.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/13 20:47
 */

@FeignClient(value = "ORDER-service-1",fallback = OrderServiceClientFallback.class)
public interface OrderServiceClient {
    @GetMapping(value = "/order-service/A/orders")
    String orders();
}