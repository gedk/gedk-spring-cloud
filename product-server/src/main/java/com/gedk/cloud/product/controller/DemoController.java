package com.gedk.cloud.product.controller;

import com.gedk.cloud.product.client.OrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/13 20:52
 */
@RestController
@RequestMapping("/product")
public class DemoController {
    @Autowired
    private OrderServiceClient client;

    @GetMapping("/orders")
    public String orders(){
        return client.orders();
    }
}
