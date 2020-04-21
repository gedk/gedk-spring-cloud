package com.gedk.cloud.order.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/14 20:54
 */
@Service
@EnableBinding(value={ISendService.class})
public class EventBus {
    @StreamListener("myInput")
    public void onReceiver1xx(Message msg){
        System.out.println("消费者1:" + msg.getTitle());
    }
}
