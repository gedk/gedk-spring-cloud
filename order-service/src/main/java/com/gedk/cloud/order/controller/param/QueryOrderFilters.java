package com.gedk.cloud.order.controller.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;


/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 16:43
 */
@Getter
@Setter
@ApiModel
public class QueryOrderFilters {
    @ApiParam(name = "orderId",value = "订单编号",defaultValue = "001")
    //@NotNull(message = "orderId不能为空")
    private String orderId;

    @ApiParam(name = "name",value = "名称",defaultValue = "name")
    //@NotNull(message = "name不能为空")
    private String name;
}
