package com.gedk.cloud.order.controller;

import com.gedk.cloud.order.controller.param.QueryOrderFilters;
import com.gedk.cloud.order.controller.vo.Result;
import com.gedk.cloud.order.dao.entity.OrderPO;
import com.gedk.cloud.order.dao.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Desc
 *
 * @author gedekun Email:gedekun@co-mall.com
 * @since 2020/4/4 14:30
 */
@RestController
@RequestMapping("/A/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping
    @ResponseBody
    public PageInfo<OrderPO> queryAllOrders(@RequestBody @Valid QueryOrderFilters filters){
        PageHelper.startPage(2,3);
        PageInfo<OrderPO> pageInfo = new PageInfo<>(orderMapper.getOrders());
        return pageInfo;
    }
}