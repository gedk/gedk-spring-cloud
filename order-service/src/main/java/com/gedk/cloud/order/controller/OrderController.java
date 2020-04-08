package com.gedk.cloud.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc
 *
 * @author gedekun Email:gedekun@co-mall.com
 * @since 2020/4/4 14:30
 */
@RestController
@RequestMapping("/A/orders")
public class OrderController {
    @GetMapping
    public String queryAllOrders(){
        return "all orders";
    }
}
