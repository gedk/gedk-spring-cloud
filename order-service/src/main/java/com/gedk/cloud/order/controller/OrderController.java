package com.gedk.cloud.order.controller;

import com.gedk.cloud.order.controller.param.QueryOrderFilters;
import com.gedk.cloud.order.controller.vo.Result;
import com.gedk.cloud.order.dao.entity.OrderPO;
import com.gedk.cloud.order.dao.mapper.OrderMapper;
import com.gedk.cloud.order.mq.ISendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Desc
 *
 * @author gedekun Email:gedekun@co-mall.com
 * @since 2020/4/4 14:30
 */
@Api(tags = "园区管理")
@RestController
@RequestMapping("/A/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISendService sendService;

    @ApiOperation("查询所有订单数据")
    @GetMapping
    public PageInfo<OrderPO> queryAllOrders(QueryOrderFilters filters){
        PageHelper.startPage(2,3);
        PageInfo<OrderPO> pageInfo = new PageInfo<>(orderMapper.getOrders());
        return pageInfo;
    }

    @GetMapping("/send")
    public void send(){
        String msg = "{\"title\":\"gedk\"}";
        Message message = MessageBuilder.withPayload(msg.getBytes()).build();
        sendService.send().send(message );
    }
}