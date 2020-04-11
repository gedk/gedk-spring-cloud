package com.gedk.cloud.order.config;

import com.gedk.cloud.order.exception.GlobalExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 16:58
 */
@Configuration
public class MyConfig {
    @Bean
    public GlobalExceptionResolver myExceptionResolver(){
        return new GlobalExceptionResolver();
    }
}
