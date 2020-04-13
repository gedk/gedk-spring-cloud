package com.gedk.cloud.product.config;

import feign.Logger;
import org.springframework.cloud.openfeign.DefaultFeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/13 21:44
 */
@Configuration
public class MyConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        DefaultFeignLoggerFactory d;
        return Logger.Level.FULL;
    }
}
