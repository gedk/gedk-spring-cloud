package com.gedk.cloud.order.controller.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 15:55
 */
@Getter
@Setter
public class Result<T> {
    private String retCode = "0000";
    private String retMsg;
    private T data;
    private boolean success;
    public Result(T data){
        this.data = data;
    }
    public Result(String retCode,String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    public boolean isSuccess(){
        return "0000".equals(retCode);
    }
}