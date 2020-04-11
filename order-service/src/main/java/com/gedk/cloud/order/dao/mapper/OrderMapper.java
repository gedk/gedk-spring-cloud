package com.gedk.cloud.order.dao.mapper;

import com.gedk.cloud.order.dao.entity.OrderPO;

import java.util.List;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 16:00
 */
public interface OrderMapper {
    List<OrderPO> getOrders();
}
