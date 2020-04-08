package com.gedk.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/4 16:15
 */
public class TimeMonitorGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    private static final String COUNT_START_TIME = "countStartTime";
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return null;
            }
        };
    }
}
