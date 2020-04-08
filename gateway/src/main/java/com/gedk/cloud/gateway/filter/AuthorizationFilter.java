package com.gedk.cloud.gateway.filter;

import com.gedk.cloud.gateway.exception.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RefreshScope
public class AuthorizationFilter implements GlobalFilter,Ordered,InitializingBean {
    private final static List<String> SHOULD_SKIP_URLS = new ArrayList<>();

    @Value("${auth-center.skip-url}")
    private String skipUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("skip-url:{}",this.skipUrls);
        String path = exchange.getRequest().getPath().value();
        if(SHOULD_SKIP_URLS.contains(path)){
            return chain.filter(exchange);
        }
        log.info("path:{}",path);
        throw new GatewayException("授权认证失败");
       // return chain.filter(exchange);
    }


    @Override
    public void afterPropertiesSet() {
        log.info("skip-url:{}",skipUrls);
        if(!StringUtils.isEmpty(this.skipUrls)){
            SHOULD_SKIP_URLS.addAll(Arrays.asList(this.skipUrls.split(".")));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
