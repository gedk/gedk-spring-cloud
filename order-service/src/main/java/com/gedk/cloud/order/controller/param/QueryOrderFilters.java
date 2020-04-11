package com.gedk.cloud.order.controller.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 16:43
 */
@Getter
@Setter
public class QueryOrderFilters {
    @NotNull(message = "orderId不能为空")
    private String orderId;

    @NotNull(message = "name不能为空")
    private String name;
}
