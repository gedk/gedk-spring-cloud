package com.gedk.cloud.order.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/14 20:50
 */
public interface ISendService {
    @Output("myOutput")
    SubscribableChannel send();

    @Input("myInput")
    SubscribableChannel receiver1();
}
